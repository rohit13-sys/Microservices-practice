package com.example.eurekaclient1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1Controller {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/client-2")
    public String client2(){
//        kafkaTemplate.send("client-2", "hey i am client -1 i want to call client-2");
        ResponseEntity<String> response=restTemplate.getForEntity("http://client-2",String.class);
        return response.getBody();
    }


    @GetMapping("/client-3")
    public String client3(){
//        kafkaTemplate.send("client-3", "hey i am client -1 i want to call client-3");
        ResponseEntity<String> response=restTemplate.getForEntity("http://client-3",String.class);
        return response.getBody();
    }
}
