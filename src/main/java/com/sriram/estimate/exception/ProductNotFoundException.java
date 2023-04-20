package com.sriram.estimate.exception;

public class ProductNotFoundException extends Exception {

    private String productId;
    private String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductNotFoundException(String productId, String businessId) {
        this.productId = productId;
        this.businessId = businessId;
    }
}
