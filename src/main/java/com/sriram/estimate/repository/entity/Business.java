package com.sriram.estimate.repository.entity;

import com.sriram.estimate.model.request.Unit;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class Business {

    @Id
    private String businessId;

    private String businessName;

    private String userId;

    private String GSTIN;

    private String contact;

    private String email;

    private Address address;

    private List<Product> products;

    @Data
    public class Address {

        private String buildingNumber;

        private String street;

        private String city;

        private String state;

        private String country;

        private String zipcode;


    }

    @Data
    public static class Product {
        private String productId;
        private String productName;
        private Double price;
        private String unit;
    }

}
