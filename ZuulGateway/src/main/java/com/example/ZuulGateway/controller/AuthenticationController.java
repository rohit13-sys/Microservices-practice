package com.example.ZuulGateway.controller;

import com.example.ZuulGateway.Modal.UserData;
import com.example.ZuulGateway.config.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> generateToken(@RequestBody UserData user) {
        if (!user.getUserName().equals("admin") || !user.getPwd().equals("admin"))
            return ResponseEntity.ok("Invalid credential");

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUserName());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello i am gateway with security");
    }

}
