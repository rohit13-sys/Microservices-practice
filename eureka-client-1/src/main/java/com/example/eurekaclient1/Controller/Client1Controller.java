package com.example.eurekaclient1.Controller;

import com.example.eurekaclient1.Modal.JsonPayload;
import com.example.eurekaclient1.Modal.UserModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class Client1Controller {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, JsonPayload> kafkaJsonTemplate;


    @GetMapping("/")
    public String hello() {
        return "Hello i am client-1";
    }


    @PostMapping("/client-1")
    public String client2(@RequestBody UserModal user) {
        HttpEntity<UserModal> request =
                new HttpEntity<>(user);
        kafkaTemplate.send("client-2", "hey i am client -1 i want to call client-2");
        try {
            ResponseEntity<String> response = restTemplate.postForEntity("http://client-2/user", request, String.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "User Already Exist";
        }
    }

    @GetMapping("/client-3")
    public String client3() {
        kafkaTemplate.send("client-3", "hey i am client -1 i want to call client-3");
        ResponseEntity<String> response = restTemplate.getForEntity("http://client-3/", String.class);
        return response.getBody();
    }

    @PostMapping("/jsonpayload")
    public String jsonPayload(@RequestBody JsonPayload payload) {
        Message<JsonPayload> message = MessageBuilder.withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, "client-3").build();
        kafkaJsonTemplate.send(message);
        return "Json Message sent to topic client-3";
    }

}
