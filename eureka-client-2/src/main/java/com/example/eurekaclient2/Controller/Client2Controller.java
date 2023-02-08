package com.example.eurekaclient2.Controller;

import com.example.eurekaclient2.DTO.UserDTO;
import com.example.eurekaclient2.Entity.UserModel;
import com.example.eurekaclient2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> userData(@RequestBody UserModel data){
        UserModel user=userService.createUser(data);
        if(user==null){
            return new ResponseEntity<>("User Already Exist!!!!!!!",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("User Created");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel data){
        boolean isUserExist=userService.findByUsername(data);
        if(isUserExist){
            return new ResponseEntity<>("Your credentials are matched",HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Your credentials are not matched",HttpStatus.BAD_REQUEST);

    }
}
