package com.sriram.estimate.service;

import com.itextpdf.text.DocumentException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public interface PDFService {

    public ResponseEntity<byte[]> generatePdf(Map<String,String> data) throws IOException, DocumentException;
}
