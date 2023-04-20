package com.sriram.estimate.service;

import com.sriram.estimate.model.request.SignUpRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface UserService {

    String createUser(SignUpRequest user);

    String signup(Map<String,String> userData);

    UserDetails loadUserByUsername(String email);
}
