# Deployment Guide

## Backend deployment

1. Provision a PostgreSQL instance.
2. Configure environment variables in production:
   - `DATABASE_URL`
   - `JWT_SECRET`
   - `OPENAI_API_KEY`
   - `STRIPE_SECRET_KEY`
   - `STRIPE_WEBHOOK_SECRET`
   - `STRIPE_PRICE_ID`
   - `ADMIN_EMAIL`
3. Build backend container:
   - `docker build -t secondbrain-ai-backend ./backend`
4. Run the backend in production mode:
   - `docker run -d --name secondbrain-backend -p 4000:4000 --env-file backend/.env secondbrain-ai-backend`
5. Run database migrations by starting the backend; migrations execute automatically on startup.

## Android deployment

1. Open the project in Android Studio.
2. Configure release signing in `app/signingConfigs`.
3. Build release bundle:
   - `./gradlew bundleRelease`
4. Upload to Google Play Console.

## Firebase deployment

1. Enable Firebase Auth, Firestore, Storage, Analytics, Crashlytics, and FCM in the Firebase console.
2. Download the final `google-services.json` to `app/`.
3. Enable Crashlytics data collection in Firebase console.
4. Configure FCM server key for backend or push gateway if needed.

## Stripe deployment

1. Create products and recurring prices in Stripe.
2. Set `STRIPE_PRICE_ID` in the deployment environment.
3. Add webhook endpoint URL in Stripe to `https://<host>/subscriptions/webhook`.
4. Verify the webhook signing secret in `STRIPE_WEBHOOK_SECRET`.

## CI/CD deployment

1. Push to the `main` branch.
2. GitHub Actions run the Android and backend pipelines.
3. Use GitHub environment secrets for Play Store and backend deployment tokens.
