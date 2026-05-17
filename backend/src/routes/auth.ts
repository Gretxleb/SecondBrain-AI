import express from "express";
import asyncHandler from "express-async-handler";
import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";
import { query } from "../db";
import { config } from "../config";
import { requireAuth } from "../middleware/auth";
import { JwtRequest } from "../types";
const router = express.Router();
router.post("/signup", asyncHandler(async (req, res) => {
  const { email, password, name } = req.body as { email: string; password: string; name?: string };
  const existing = await query("SELECT id FROM users WHERE email = $1", [email]);
  if (existing.rows.length) {
    res.status(409).json({ error: "Email already in use" });
    return;
  }
  const passwordHash = await bcrypt.hash(password, 12);
  const created = await query("INSERT INTO users (email, password_hash, name) VALUES ($1, $2, $3) RETURNING id, email, name", [email, passwordHash, name || null]);
  const user = created.rows[0];
  const token = jwt.sign({ id: user.id, email: user.email, name: user.name }, config.jwtSecret, { expiresIn: "30d" });
  res.status(201).json({ token, user });
}));
router.post("/login", asyncHandler(async (req, res) => {
  const { email, password } = req.body as { email: string; password: string };
  const result = await query("SELECT id, email, name, password_hash FROM users WHERE email = $1", [email]);
  if (!result.rows.length) {
    res.status(401).json({ error: "Invalid credentials" });
    return;
  }
  const user = result.rows[0] as { id: string; email: string; name?: string; password_hash: string };
  const valid = await bcrypt.compare(password, user.password_hash);
  if (!valid) {
    res.status(401).json({ error: "Invalid credentials" });
    return;
  }
  const token = jwt.sign({ id: user.id, email: user.email, name: user.name }, config.jwtSecret, { expiresIn: "30d" });
  res.status(200).json({ token, user: { id: user.id, email: user.email, name: user.name } });
}));
router.get("/me", requireAuth, asyncHandler(async (req: JwtRequest, res) => {
  const user = req.user;
  if (!user) {
    res.status(404).json({ error: "User not found" });
    return;
  }
  res.status(200).json({ user });
}));
export { router as authRouter };
