package com.springboot.payment_gateway_demo.controller;

import com.springboot.payment_gateway_demo.service.EmailService;
import com.springboot.payment_gateway_demo.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    public final PdfService pdfService;
    public final EmailService emailService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@RequestBody Map<String, String> data) throws Exception {

        String name = data.get("name");
        String email = data.get("email");

        byte[] pdf = pdfService.generatePdf(name, "500");

        emailService.sendInvoice(email, pdf);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
