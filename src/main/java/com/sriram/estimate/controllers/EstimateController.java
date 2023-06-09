package com.sriram.estimate.controllers;

import com.itextpdf.text.DocumentException;
import com.sriram.estimate.service.EstimateService;
import com.sriram.estimate.service.impl.PDFServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/estimates")
public class EstimateController {

    @Autowired
    private EstimateService estimateService;

    @Autowired
    PDFServiceImpl pdfService;

    @PostMapping
    public String createEstimate(@RequestBody Map<String, String> request) {
        return estimateService.createEstimate(request);
    }

    @PostMapping("/{estimateId}/items")
    public Map<String, Object> addEstimateItem(@RequestBody Map<String, Object> request, @PathVariable String estimateId) {
        return estimateService.addEstimateItem(estimateId, request);
    }

    @GetMapping("/{estimateId}/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@PathVariable  String estimateId) throws DocumentException, IOException {
        return  estimateService.exportEstimateToPdf(estimateId);
    }

    @GetMapping("/testpdf/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() throws DocumentException, IOException {
        return pdfService.generatePdf(new HashMap<>());
    }

}
