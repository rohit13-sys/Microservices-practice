package com.example.eurekaclient1.Controller;

import com.example.eurekaclient1.Modal.UserModal;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/")
    public String hello(){
        return "Hello i am client-1";
    }


    @PostMapping("/client-2/user")
    public String client2(@RequestBody UserModal user){

        HttpEntity<UserModal> request =
                new HttpEntity<>(user);
        kafkaTemplate.send("client-2", "hey i am client -1 i want to call client-2");
        try{
            ResponseEntity<String> response=restTemplate.postForEntity("http://client-2/user",request, String.class);
            return response.getBody().toString();
        }catch (Exception e){
            e.printStackTrace();
            return "Some exception Occurred";
        }



    }


    @GetMapping("/client-3")
    public String client3(){
        kafkaTemplate.send("client-3", "hey i am client -1 i want to call client-3");
        ResponseEntity<String> response=restTemplate.getForEntity("http://client-3/",String.class);
        return response.getBody();
    }




}
