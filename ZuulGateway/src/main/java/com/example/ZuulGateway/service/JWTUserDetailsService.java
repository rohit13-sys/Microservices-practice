package com.example.ZuulGateway.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(userName.equalsIgnoreCase("admin")){
            return new User("admin","admin",new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User Not Found with userName : "+userName);
        }
    }
}
