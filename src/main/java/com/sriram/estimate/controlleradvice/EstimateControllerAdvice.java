package com.sriram.estimate.controlleradvice;

import com.sriram.estimate.exception.BusinessNotFoundException;
import com.sriram.estimate.exception.ProductNotFoundException;
import com.sriram.estimate.model.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice(basePackages = "com.sriram.estimate.controllers")
public class EstimateControllerAdvice {

    private final String BUSINESS_NOT_FOUND_EXP_MSSG = "Business not found with id %s";

    private final String PRODUCT_NOT_FOUND_EXP_MSSG = "Product not found with id %s in business with id %s";

    @ExceptionHandler(value = {BusinessNotFoundException.class})
    public ResponseEntity<ErrorMessage> businessNotFoundException(BusinessNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage("", String.format(BUSINESS_NOT_FOUND_EXP_MSSG, ex.getBusinessId()), new Date().toString());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage("", String.format(PRODUCT_NOT_FOUND_EXP_MSSG, ex.getProductId(), ex.getBusinessId()), new Date().toString());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
}
