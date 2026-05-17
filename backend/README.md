# SecondBrain AI Backend

This backend provides authentication, note sync, AI services, subscription management, PDF export, and Stripe webhook handling.

## Local development

1. Copy `.env.example` to `.env`
2. Fill values with local secrets
3. Start PostgreSQL and backend:
   - `docker compose up --build`
4. Or run locally:
   - `npm install`
   - `npm run dev`

## Scripts

- `npm run dev` — start development server with live reload
- `npm run build` — compile TypeScript to `dist`
- `npm start` — run compiled server
- `npm run lint` — lint TypeScript code
- `npm test` — run unit tests

## API routes

- `POST /auth/signup`
- `POST /auth/login`
- `GET /auth/me`
- `GET /notes`
- `POST /notes`
- `PUT /notes/:id`
- `DELETE /notes/:id`
- `POST /ai/chat`
- `POST /ai/summary`
- `POST /ai/flashcards`
- `POST /ai/memory`
- `POST /ai/image-analysis`
- `POST /subscriptions/create-session`
- `POST /subscriptions/webhook`
- `POST /export/note`
