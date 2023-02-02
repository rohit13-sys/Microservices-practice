package com.example.eurekaclient2.Repository;

import com.example.eurekaclient2.Entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {


    public  Optional<UserModel> findByUserName(String username);
}
