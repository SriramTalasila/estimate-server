package com.sriram.estimate.repository;

import com.sriram.estimate.repository.entity.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends MongoRepository<Template,String> {

    public Template findTemplateByKey(String key);
}
