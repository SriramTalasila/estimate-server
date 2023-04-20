package com.sriram.estimate.model.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String usernameOrEmail;
    private String password;


}
