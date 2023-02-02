package com.example.eurekaclient2.service;

import com.example.eurekaclient2.Entity.UserModel;
import com.example.eurekaclient2.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserModel createUser(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    @Override
    public boolean findByUsername(UserModel data) {
        Optional<UserModel> user=userRepo.findByUserName(data.getUserName());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!user.isEmpty()){
            if(encoder.matches(data.getPassword(), user.get().getPassword())) {
                return true;
            }
        }
        return false;

    }
}
