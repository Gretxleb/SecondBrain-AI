import express from "express";
import asyncHandler from "express-async-handler";
import { query } from "../db";
import { requireAuth } from "../middleware/auth";
import { JwtRequest } from "../types";
const router = express.Router();
router.use(requireAuth);
router.get("/", asyncHandler(async (req: JwtRequest, res) => {
  const userId = req.user?.id;
  const result = await query("SELECT id, title, content, metadata, created_at, updated_at FROM notes WHERE user_id = $1 ORDER BY updated_at DESC", [userId]);
  res.status(200).json({ notes: result.rows });
}));
router.post("/", asyncHandler(async (req: JwtRequest, res) => {
  const userId = req.user?.id;
  const { title, content, metadata } = req.body;
  const created = await query("INSERT INTO notes (user_id, title, content, metadata) VALUES ($1, $2, $3, $4) RETURNING id, title, content, metadata, created_at, updated_at", [userId, title, content, metadata ?? {}]);
  res.status(201).json({ note: created.rows[0] });
}));
router.put("/:id", asyncHandler(async (req: JwtRequest, res) => {
  const userId = req.user?.id;
  const { title, content, metadata } = req.body;
  const noteId = req.params.id;
  const updated = await query("UPDATE notes SET title = $1, content = $2, metadata = $3, updated_at = now() WHERE id = $4 AND user_id = $5 RETURNING id, title, content, metadata, created_at, updated_at", [title, content, metadata ?? {}, noteId, userId]);
  if (!updated.rows.length) {
    res.status(404).json({ error: "Note not found" });
    return;
  }
  res.status(200).json({ note: updated.rows[0] });
}));
router.delete("/:id", asyncHandler(async (req: JwtRequest, res) => {
  const userId = req.user?.id;
  const noteId = req.params.id;
  await query("DELETE FROM notes WHERE id = $1 AND user_id = $2", [noteId, userId]);
  res.status(204).send();
}));
export { router as notesRouter };
