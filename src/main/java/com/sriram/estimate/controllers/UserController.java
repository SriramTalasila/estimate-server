package com.sriram.estimate.controllers;

import com.sriram.estimate.model.request.LoginRequest;
import com.sriram.estimate.model.request.SignUpRequest;
import com.sriram.estimate.repository.entity.User;
import com.sriram.estimate.service.UserService;
import com.sriram.estimate.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String createUser(@RequestBody SignUpRequest user) {
        log.info("Inside create user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> signin(@RequestBody Map<String,String> login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.get("username"),
                        login.get("password")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.get("username"));
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/user/{email}")
    public User getUser(@PathVariable String email){
        UserDetails details =  userDetailsService.loadUserByUsername(email);
        return new User();
    }
}
