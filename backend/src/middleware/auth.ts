import { NextFunction, Request, Response } from "express";
import jwt from "jsonwebtoken";
import { config } from "../config";
import { JwtRequest } from "../types";
export function requireAuth(req: JwtRequest, res: Response, next: NextFunction): void {
  const authorization = req.headers.authorization;
  if (!authorization || !authorization.startsWith("Bearer ")) {
    res.status(401).json({ error: "Unauthorized" });
    return;
  }
  const token = authorization.replace("Bearer ", "");
  try {
    const payload = jwt.verify(token, config.jwtSecret) as { id: string; email: string; name?: string };
    req.user = { id: payload.id, email: payload.email, name: payload.name };
    next();
  } catch (error) {
    res.status(401).json({ error: "Invalid token" });
  }
}
