package com.sriram.estimate.repository;

import com.sriram.estimate.repository.entity.Estimate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateRepository extends MongoRepository<Estimate,String> {
}
