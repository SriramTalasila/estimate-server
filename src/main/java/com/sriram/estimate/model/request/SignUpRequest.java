package com.sriram.estimate.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {

    private String firstName;

    private String lastName;

    private String email;

    private Date lastLoginTime;

    private String password;


}
