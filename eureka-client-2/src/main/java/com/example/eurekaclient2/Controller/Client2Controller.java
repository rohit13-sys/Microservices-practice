package com.example.eurekaclient2.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client-2")
public class Client2Controller {

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
}
