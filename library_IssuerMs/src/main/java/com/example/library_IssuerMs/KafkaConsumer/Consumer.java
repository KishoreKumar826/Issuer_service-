package com.example.library_IssuerMs.KafkaConsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
     @KafkaListener(topics="BookCopies", groupId="a")
	public void consumer(String message){
System.out.println("Consumer 1 received "+message);
	}

}
