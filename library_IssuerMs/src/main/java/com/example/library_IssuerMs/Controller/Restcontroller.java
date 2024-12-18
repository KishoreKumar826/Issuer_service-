package com.example.library_IssuerMs.Controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontroller {

    @GetMapping("/")
    public static String welcome(){
        return "Welcome to issuer service";  
    }

    @KafkaListener(topics="BookCopies", groupId="a")
	public void consumer(String message){
System.out.println("Consumer 1 received "+message);
	}

}
