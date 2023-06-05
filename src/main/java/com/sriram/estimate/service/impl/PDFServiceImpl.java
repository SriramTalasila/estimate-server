package com.sriram.estimate.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.sriram.estimate.service.PDFService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

//-----------

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

//------

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;


@Component
public class PDFServiceImpl implements PDFService {


    private static String htmlTemplate = "";

    static {
        if (htmlTemplate.isBlank()) {
            try {
                File resource = new ClassPathResource(
                        "templates/pdfTemplatev2.html").getFile();
                htmlTemplate = new String(
                        Files.readAllBytes(resource.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    @Override
    public ResponseEntity<byte[]> generatePdf(Map<String, String> data) throws IOException, DocumentException {
        // return generatePdfv2(data);
        String template = new String(htmlTemplate);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            template = template.replace(entry.getKey(), entry.getValue());
        }
        Document document = new Document(PageSize.A4, 25, 25, 25, 25);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();
        InputStream is = new ByteArrayInputStream(template.getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        document.close();
        writer.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"");
        byte[] pdfBytes = baos.toByteArray();
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);

    }

    @SneakyThrows
    public ResponseEntity<byte[]> generatePdfv2(Map<String, String> data) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setRenderingMode(RenderingMode.FILL_STROKE_CLIP);
        contentStream.beginText();
        contentStream.appendRawCommands(htmlTemplate.getBytes());
        contentStream.endText();
        contentStream.close();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        byte[] pdfBytes = outputStream.toByteArray();

        document.close();
        outputStream.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"document.pdf\"");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);

    }


}
