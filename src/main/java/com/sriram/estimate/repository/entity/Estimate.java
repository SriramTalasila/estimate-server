package com.sriram.estimate.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class Estimate {

    @Id
    private String estimateId;

    private String businessId;

    private Date createdDate;

    private String customerName;

    private String customerContact;

    private List<EstimateItem> estimateItems;

    @Data
    public static class EstimateItem{
        private String estimateItemId;
        private  String itemName;
        private Double price;
        private Double quantity;
        private String quantityUnit;
        private Double discountPercentage;

    }
}
