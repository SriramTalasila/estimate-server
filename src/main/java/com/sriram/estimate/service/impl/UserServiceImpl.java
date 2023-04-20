package com.sriram.estimate.service.impl;

import com.sriram.estimate.model.request.SignUpRequest;
import com.sriram.estimate.repository.UserRepository;
import com.sriram.estimate.repository.entity.User;
import com.sriram.estimate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public String createUser(SignUpRequest user) {
        User userEntity = new com.sriram.estimate.repository.entity.User();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassword((user.getPassword()));
        userEntity.setLastLoginTime(new Date());
        userEntity.setEnabled(false);
        userRepository.save(userEntity);

        return "user created successfully";
    }

    @Override
    public String signup(Map<String, String> userData) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return null;
    }


}
