package com.example.eurekaclient3.Controller;

import com.example.eurekaclient3.modal.JsonData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Client3Controller {

    @GetMapping("/")
    public String hello(){
        return "hello I am client - 3";
    }

    @KafkaListener(topics = "client-3", groupId = "xyz")
    public void listenGroupFoo(String message) throws JsonProcessingException {
        ObjectMapper map=new ObjectMapper();
        JsonData data=map.readValue(message, JsonData.class);
        System.out.println(" id: "+data.getId() + " firstname : "
                +data.getFirstname()+ " lastname : "+data.getLastName());
        System.out.println("Received Message in group foo: " + data.toString());
    }


    @Value("${server.port}")
    private String port;

    @GetMapping("/info")
    public ResponseEntity<String> showOrderInfo() {
        return ResponseEntity.ok("FROM ORDER SERVICE, Port# is: " + port);
    }

    @KafkaListener(topics = "wikimedia", groupId = "xyz")
    public void wikimediadata(String msg){
        System.out.println("\n\n"+msg+"\n\n");
    }
}
