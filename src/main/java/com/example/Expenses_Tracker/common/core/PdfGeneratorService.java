package com.example.Expenses_Tracker.common.core;

import com.example.Expenses_Tracker.expenses.entity.Expenses;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import java.io.ByteArrayOutputStream;
import java.time.YearMonth;
import java.util.List;

@Service
public class PdfGeneratorService {
    public byte[] generatePdfOfExpenses( final List<Expenses> expenseList) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
           document.add(new Paragraph("Expense Receipt")
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBold()
                            .setFontSize(18)
                            .setMarginBottom(10));

            Table table = new Table(UnitValue.createPercentArray(new float[]{2, 5, 3, 3}))
                .useAllAvailableWidth();
            table.addHeaderCell(new Cell().add(new Paragraph("Category").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Description").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Date").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Amount").setBold()));


            for (Expenses expense : expenseList) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(expense.getCategory()))));
                table.addCell(new Cell().add(new Paragraph(expense.getDescription())));
               table.addCell(new Cell().add(new Paragraph(String.valueOf(expense.getDate()))));
                table.addCell(new Cell().add(new Paragraph(String.format("$%.2f", expense.getAmount()))));
            }
            document.add(table);


            // Close document
            document.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generating PDF", e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
