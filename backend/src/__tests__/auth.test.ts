import request from "supertest";
import express from "express";
jest.mock("../db", () => ({
  query: jest.fn()
}));
const { authRouter } = require("../routes/auth");
const { query } = require("../db");
const app = express();
app.use(express.json());
app.use("/auth", authRouter);
describe("Auth routes", () => {
  it("returns 401 for invalid login", async () => {
    query.mockResolvedValueOnce({ rows: [] });
    const response = await request(app).post("/auth/login").send({ email: "test@example.com", password: "password" });
    expect(response.status).toBe(401);
  });
});
