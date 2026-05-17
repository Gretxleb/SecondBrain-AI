import express from "express";
import asyncHandler from "express-async-handler";
import PDFDocument from "pdfkit";
const router = express.Router();
router.post("/note", asyncHandler(async (req, res) => {
  const { title, content } = req.body;
  res.setHeader("Content-Type", "application/pdf");
  res.setHeader("Content-Disposition", `attachment; filename="${title?.replace(/[^a-zA-Z0-9_-]/g, "_") || "note"}.pdf"`);
  const pdf = new PDFDocument({ size: "A4", margin: 48 });
  pdf.pipe(res);
  pdf.fontSize(20).text(title || "SecondBrain AI Note", { underline: true, align: "center" });
  pdf.moveDown();
  pdf.fontSize(12).text(content || "", { align: "left" });
  pdf.end();
}));
export { router as pdfRouter };
