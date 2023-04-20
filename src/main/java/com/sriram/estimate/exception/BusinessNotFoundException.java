package com.sriram.estimate.exception;

public class BusinessNotFoundException extends Exception{

    private  String businessId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public BusinessNotFoundException(String businessId){
        this.businessId = businessId;
    }

}
