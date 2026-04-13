package com.springboot.payment_gateway_demo.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;


@Service
public class PdfService {

    public byte[] generatePdf(String name, String amount) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Invoice"));
        document.add(new Paragraph("Name: " + name));
        document.add(new Paragraph("Amount: ₹" + amount));

        document.close();

        return out.toByteArray();
    }
}
