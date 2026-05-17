import dotenv from "dotenv";
dotenv.config();
function getEnv(name: string, defaultValue?: string): string {
  const value = process.env[name] ?? defaultValue;
  if (!value) {
    throw new Error(`Missing environment variable: ${name}`);
  }
  return value;
}
export const config = {
  port: Number(process.env.PORT ?? 4000),
  databaseUrl: getEnv("DATABASE_URL", "postgresql://localhost:5432/secondbrain"),
  databaseSsl: process.env.DATABASE_SSL === "true",
  jwtSecret: getEnv("JWT_SECRET", "dev-jwt-secret"),
  openAiApiKey: process.env.OPENAI_API_KEY ?? "",
  stripeSecretKey: process.env.STRIPE_SECRET_KEY ?? "",
  stripeWebhookSecret: process.env.STRIPE_WEBHOOK_SECRET ?? "",
  stripePriceId: process.env.STRIPE_PRICE_ID ?? "price_test",
  adminEmail: process.env.ADMIN_EMAIL ?? "admin@secondbrain.ai"
};
