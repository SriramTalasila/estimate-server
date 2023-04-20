package com.sriram.estimate.repository;

import com.sriram.estimate.repository.entity.Business;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface BusinessRepository extends MongoRepository<Business, String> {

    Optional<Business> findBusinessByUserId(String userId);

    Optional<Business> findBusinessByBusinessId(String businessId);
}
