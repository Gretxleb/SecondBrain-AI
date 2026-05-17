import express from "express";
import asyncHandler from "express-async-handler";
import OpenAI from "openai";
import { query } from "../db";
import { config } from "../config";
const openai = new OpenAI({ apiKey: config.openAiApiKey });
const router = express.Router();
router.post("/chat", asyncHandler(async (req, res) => {
  const { messages } = req.body;
  const completion = await openai.chat.completions.create({
    model: "gpt-4o-mini",
    messages
  });
  res.status(200).json({ result: completion.choices[0]?.message?.content ?? "" });
}));
router.post("/summary", asyncHandler(async (req, res) => {
  const { noteText } = req.body;
  const completion = await openai.chat.completions.create({
    model: "gpt-4o-mini",
    messages: [{ role: "user", content: `Create a concise summary of the following note:\n${noteText}` }]
  });
  res.status(200).json({ summary: completion.choices[0]?.message?.content ?? "" });
}));
router.post("/flashcards", asyncHandler(async (req, res) => {
  const { noteText } = req.body;
  const completion = await openai.chat.completions.create({
    model: "gpt-4o-mini",
    messages: [{ role: "user", content: `Generate four flashcards with questions and answers from this note text:\n${noteText}` }]
  });
  res.status(200).json({ flashcards: completion.choices[0]?.message?.content ?? "" });
}));
router.post("/memory", asyncHandler(async (req, res) => {
  const { userId, title, content } = req.body;
  const stored = await query("INSERT INTO memories (user_id, title, content) VALUES ($1, $2, $3) RETURNING id, title, content, created_at", [userId, title, content]);
  res.status(201).json({ memory: stored.rows[0] });
}));
router.post("/image-analysis", asyncHandler(async (req, res) => {
  const { imageUrl } = req.body;
  const completion = await openai.chat.completions.create({
    model: "gpt-4o-mini",
    messages: [{ role: "user", content: `Analyze the image available at ${imageUrl} and return a detailed description of objects, actions, and recommendations.` }]
  });
  res.status(200).json({ analysis: completion.choices[0]?.message?.content ?? "" });
}));
export { router as openAiRouter };
