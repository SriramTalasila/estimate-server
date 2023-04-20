package com.sriram.estimate.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.sriram.estimate.repository.EstimateRepository;
import com.sriram.estimate.repository.TemplateRepository;
import com.sriram.estimate.repository.entity.Estimate;
import com.sriram.estimate.service.EstimateService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class EstimateServiceImpl implements EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;

    @Autowired
    PDFServiceImpl pdfService;

    @Autowired
    TemplateRepository templateRepository;

    @Override
    public String createEstimate(Map<String, String> request) {
        Estimate estimate = new Estimate();
        estimate.setEstimateId(UUID.randomUUID().toString());
        estimate.setCreatedDate(new Date());
        estimate.setCustomerName(request.get("customerName"));
        estimate.setBusinessId(request.get("businessId"));
        estimate.setCustomerContact(request.get("customerContact"));
        estimate.setEstimateItems(new ArrayList<>());
        estimateRepository.save(estimate);
        return estimate.getEstimateId();
    }

    @Override
    public Map<String, Object> addEstimateItem(String estimateId, Map<String, Object> request) {
        Estimate.EstimateItem estimateItem = new Estimate.EstimateItem();
        estimateItem.setEstimateItemId(UUID.randomUUID().toString());
        estimateItem.setItemName((String) request.get("itemName"));
        estimateItem.setPrice((Double) request.get("price"));
        estimateItem.setQuantity((Double) request.get("quantity"));
        estimateItem.setQuantityUnit((String) request.get("unit"));
        estimateItem.setDiscountPercentage((Double) request.get("discountPercentage"));
        Optional<Estimate> estimate = estimateRepository.findById(estimateId);
        Estimate estimateEntity = estimate.orElseThrow();
        estimateEntity.getEstimateItems().add(estimateItem);
        estimateRepository.save(estimateEntity);
        return new ObjectMapper().convertValue(estimateItem, Map.class);
    }

    @Override
    public ResponseEntity<byte[]> exportEstimateToPdf(String estimateId) throws DocumentException, IOException {
        String trTemplate = templateRepository.findTemplateByKey("tableRow").getValue();
        Optional<Estimate> estimate = estimateRepository.findById(estimateId);
        Estimate estimateEntity = estimate.orElseThrow();
        Map<String,String> data = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int ind = 1;
        for (Estimate.EstimateItem item : estimateEntity.getEstimateItems()){
            String tr = new String(trTemplate);
            tr = tr.replace("{sNo}",String.valueOf(ind));
            tr = tr.replace("{itemName}",item.getItemName());
            tr = tr.replace("{itemQuantity}",String.valueOf(item.getQuantity()));
            tr = tr.replace("{itemPrice}",String.valueOf(item.getPrice()));
            tr = tr.replace("{totalItemPrice}",String.valueOf(item.getPrice() * item.getQuantity()));
            ind++;
            sb.append(tr);
        }
        data.put("{trData}", sb.toString());
        return pdfService.generatePdf(data);
    }
}
