import express from "express";
import helmet from "helmet";
import cors from "cors";
import { json } from "body-parser";
import { config } from "./config";
import { authRouter } from "./routes/auth";
import { openAiRouter } from "./routes/openai";
import { notesRouter } from "./routes/notes";
import { subscriptionRouter } from "./routes/subscription";
import { pdfRouter } from "./routes/pdf";
import { executeMigrations } from "./db";
import { errorHandler } from "./middleware/errorHandler";
const app = express();
app.use(helmet());
app.use(cors());
app.use(json({ limit: "10mb" }));
app.get("/health", (_req, res) => {
  res.status(200).json({ status: "ok" });
});
app.use("/auth", authRouter);
app.use("/notes", notesRouter);
app.use("/ai", openAiRouter);
app.use("/subscriptions", subscriptionRouter);
app.use("/export", pdfRouter);
app.use(errorHandler);
executeMigrations()
  .then(() => {
    app.listen(config.port, () => {
      console.log(`Server listening on port ${config.port}`);
    });
  })
  .catch((error) => {
    console.error(error);
    process.exit(1);
  });
