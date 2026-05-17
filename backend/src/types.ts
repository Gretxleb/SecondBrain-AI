import { Request } from "express";
export interface UserPayload {
  id: string;
  email: string;
  name?: string;
}
export interface JwtRequest extends Request {
  user?: UserPayload;
}
export interface ChatRequest {
  messages: { role: string; content: string }[];
}
export interface CreateFlashcardsRequest {
  noteText: string;
}
export interface CreateSummaryRequest {
  noteText: string;
}
export interface MemoryRequest {
  userId: string;
  title: string;
  content: string;
}
export interface ImageAnalysisRequest {
  imageUrl: string;
}
