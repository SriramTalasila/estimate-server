package com.sriram.estimate.controllers;

import com.sriram.estimate.exception.BusinessNotFoundException;
import com.sriram.estimate.repository.entity.Business;
import com.sriram.estimate.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @PostMapping
    public String addBusiness(@RequestBody Business business) {
        return businessService.addBusiness(business);
    }

    @GetMapping
    public Business getBusiness() {
        return businessService.getBusiness();
    }

    @GetMapping("/{businessId}/products")
    public List<Business.Product> getProducts(@PathVariable String businessId) throws BusinessNotFoundException {
        return businessService.getProducts(businessId);
    }

    @PostMapping("/{businessId}/products")
    public String addProduct(@RequestBody Business.Product product, @PathVariable String businessId) throws BusinessNotFoundException {
        return businessService.addProduct(businessId, product);
    }

    @DeleteMapping("/{businessId}/products/{productId}")
    public String deleteProduct(@PathVariable String businessId, @PathVariable String productId) throws BusinessNotFoundException {
        return businessService.deleteProduct(businessId, productId);
    }

    @GetMapping("/{businessId}/products/{productId}")
    public Business.Product getProduct(@PathVariable String businessId, @PathVariable String productId) throws Exception {
        return businessService.getProduct(businessId, productId);
    }
}
