import express from "express";
import Stripe from "stripe";
import asyncHandler from "express-async-handler";
import { query } from "../db";
import { config } from "../config";
import { requireAuth } from "../middleware/auth";
import { JwtRequest } from "../types";
const stripe = new Stripe(config.stripeSecretKey, { apiVersion: "2024-11-21" });
const router = express.Router();
router.post("/create-session", requireAuth, asyncHandler(async (req: JwtRequest, res) => {
  const user = req.user;
  const session = await stripe.checkout.sessions.create({
    payment_method_types: ["card"],
    mode: "subscription",
    customer_email: user?.email ?? undefined,
    line_items: [{ price: config.stripePriceId, quantity: 1 }],
    success_url: `${req.protocol}://${req.get("host")}/success`,
    cancel_url: `${req.protocol}://${req.get("host")}/cancel`
  });
  await query("INSERT INTO subscriptions (user_id, stripe_session_id, price_id, status) VALUES ($1, $2, $3, $4)", [user?.id, session.id, config.stripePriceId, session.payment_status || "incomplete"]);
  res.status(200).json({ sessionId: session.id, url: session.url });
}));
router.post("/webhook", express.raw({ type: "application/json" }), asyncHandler(async (req, res) => {
  const signature = req.headers["stripe-signature"] as string;
  const event = stripe.webhooks.constructEvent(req.body, signature, config.stripeWebhookSecret);
  if (event.type === "checkout.session.completed") {
    const session = event.data.object as Stripe.Checkout.Session;
    const customerEmail = session.customer_email;
    const subscriptionId = session.subscription as string;
    if (customerEmail) {
      const userResult = await query("SELECT id FROM users WHERE email = $1", [customerEmail]);
      if (userResult.rows.length) {
        const userId = userResult.rows[0].id;
        await query("UPDATE subscriptions SET stripe_subscription_id = $1, status = $2, updated_at = now() WHERE user_id = $3", [subscriptionId, "active", userId]);
      }
    }
  }
  if (event.type === "invoice.payment_failed") {
    const invoice = event.data.object as Stripe.Invoice;
    const customerEmail = invoice.customer_email;
    if (customerEmail) {
      await query("UPDATE subscriptions SET status = $1 WHERE user_id = (SELECT id FROM users WHERE email = $2)", ["past_due", customerEmail]);
    }
  }
  res.status(200).json({ received: true });
}));
export { router as subscriptionRouter };
