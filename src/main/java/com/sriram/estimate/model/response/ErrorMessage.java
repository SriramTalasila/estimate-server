package com.sriram.estimate.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorMessage implements Serializable {

    private String errorCode;
    private String message;
    private String date;
}
