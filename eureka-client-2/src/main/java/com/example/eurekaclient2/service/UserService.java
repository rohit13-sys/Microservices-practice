package com.example.eurekaclient2.service;

import com.example.eurekaclient2.DTO.UserDTO;
import com.example.eurekaclient2.Entity.UserModel;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
    public UserModel createUser(UserModel user);

    public boolean findByUsername(UserModel data);
}
