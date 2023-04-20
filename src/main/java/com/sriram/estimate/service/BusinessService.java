package com.sriram.estimate.service;

import com.sriram.estimate.exception.BusinessNotFoundException;
import com.sriram.estimate.repository.entity.Business;

import java.util.List;

public interface BusinessService {

    public String addBusiness(Business business);

    public Business getBusiness();

    public String addProduct(String businessId, Business.Product product) throws BusinessNotFoundException;

    public Business.Product getProduct(String businessId, String productId) throws Exception;

    public List<Business.Product> getProducts(String businessId) throws BusinessNotFoundException;

    public String deleteProduct(String businessId, String productId) throws BusinessNotFoundException;


}
