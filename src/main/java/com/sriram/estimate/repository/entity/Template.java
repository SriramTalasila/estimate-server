package com.sriram.estimate.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Template {
    @Id
    private String id;

    private String key;
    private String value;
}
