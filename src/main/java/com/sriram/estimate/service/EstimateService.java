package com.sriram.estimate.service;

import com.itextpdf.text.DocumentException;
import com.sriram.estimate.repository.entity.Estimate;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public interface EstimateService {

    public String createEstimate(Map<String,String> request);

    public Map<String,Object> addEstimateItem(String estimateId,Map<String,Object> request);

    public ResponseEntity<byte[]> exportEstimateToPdf(String estimateId) throws DocumentException, IOException;

}
