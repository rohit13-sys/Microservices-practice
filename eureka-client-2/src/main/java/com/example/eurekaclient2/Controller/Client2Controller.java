package com.example.eurekaclient2.Controller;

import com.example.eurekaclient2.DTO.UserDTO;
import com.example.eurekaclient2.Entity.UserModel;
import com.example.eurekaclient2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController

public class Client2Controller {

    @Autowired
    UserService userService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String hello(){
        return "Hello i am client 2";
    }

    @KafkaListener(topics = "client-2", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

    @GetMapping("/info")
    public ResponseEntity<String> showPaymentInfo() {
        return ResponseEntity.ok("FROM PAYMENT SERVICE, Port# is: " + port);
    }

    @PostMapping("/user")
    public String userData(@RequestBody UserModel data){
//        ModelMapper map=new ModelMapper();
        UserModel user=new UserModel();
//        map.map(data,user);
        ObjectMapper map=new ObjectMapper();
        user=map.convertValue(data,UserModel.class);
//        user.setUserName(user.getUserName());
//        user.setPassword(user.getPassword());
        userService.createUser(user);
        return "Data inserted";
    }
}
