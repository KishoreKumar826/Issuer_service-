package com.example.library_IssuerMs.KafkaConsumer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
	public static Integer availableSize;
	private static CompletableFuture<Void> future = new CompletableFuture<>();

	@KafkaListener(topics = "produceAvailableSize", groupId = "a")
	public void consumer_Avaialblesize(String message) {
		System.out.println("consumer_Availablesize " + message);
		availableSize = Integer.parseInt(message);
		future.complete(null); // Complete the future once size is updated
		
	}

	public static Integer getAvailablesize(){
		return availableSize;
	}

	// waits to get the available size for synchronous communication
	public static void waitForUpdate() throws InterruptedException {
        try {
            future.get(); // Wait for the future to complete
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
