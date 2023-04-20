package com.sriram.estimate.service.impl;

import com.sriram.estimate.exception.BusinessNotFoundException;
import com.sriram.estimate.exception.ProductNotFoundException;
import com.sriram.estimate.repository.BusinessRepository;
import com.sriram.estimate.repository.entity.Business;
import com.sriram.estimate.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    static {

    }

    @Override
    public String addBusiness(Business business) {
        if (business != null) {
            business.setBusinessId(UUID.randomUUID().toString());
            //business.setUserUUID();
            businessRepository.save(business);
            return "Added Sucessfully";
        } else {
            return "Failed";
        }
    }

    @Override
    public Business getBusiness() {
        String userId = "c8f4527e-99a6-4c52-b526-38bdd76f2c8b";
        return businessRepository.findBusinessByUserId(userId).orElse(null);
    }

    @Override
    public String addProduct(String businessId, Business.Product product) throws BusinessNotFoundException {
        Optional<Business> business = businessRepository.findBusinessByBusinessId(businessId);
        if (business.isEmpty())
            throw new BusinessNotFoundException(businessId);
        product.setProductId(UUID.randomUUID().toString());
        if (business.get().getProducts() == null)
            business.get().setProducts(new ArrayList<>());
        business.get().getProducts().add(product);
        businessRepository.save(business.get());
        return "Saved Successfully";
    }

    @Override
    public Business.Product getProduct(String businessId, String productId) throws Exception {
        Optional<Business> business = businessRepository.findBusinessByBusinessId(businessId);
        Business businessEntity = business.orElseThrow(() -> new BusinessNotFoundException(businessId));
        Optional<Business.Product> product = businessEntity.getProducts().stream().filter(p -> p.getProductId().equalsIgnoreCase(productId)).findAny();
        return product.orElseThrow(() -> new ProductNotFoundException(productId, businessId));
    }

    @Override
    public List<Business.Product> getProducts(String businessId) throws BusinessNotFoundException {
        Optional<Business> business = businessRepository.findBusinessByBusinessId(businessId);
        Business businessEntity = business.orElseThrow(() -> new BusinessNotFoundException(businessId));
        return businessEntity.getProducts();
    }

    @Override
    public String deleteProduct(String businessId, String productId) throws BusinessNotFoundException {
        Optional<Business> business = businessRepository.findBusinessByBusinessId(businessId);
        Business businessEntity = business.orElseThrow(() -> new BusinessNotFoundException(businessId));
        businessEntity.setProducts(businessEntity.getProducts().stream().filter(p -> !p.getProductId().equalsIgnoreCase(productId)).collect(Collectors.toList()));
        businessRepository.save(businessEntity);
        return "Deleted Successfully";
    }
}
