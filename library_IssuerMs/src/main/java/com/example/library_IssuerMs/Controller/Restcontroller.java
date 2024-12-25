package com.example.library_IssuerMs.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_IssuerMs.Domain.Issuer;
import com.example.library_IssuerMs.KafkaConsumer.Consumer;
import com.example.library_IssuerMs.Repository.repo;

@RestController
public class Restcontroller {

    @Autowired
    private KafkaTemplate<String, String> kafka;
    @Autowired
    private repo rep;
    @GetMapping("/")
    public static String welcome() {
        return "Welcome to issuer service";
    }

    // @KafkaListener(topics="BookCopies", groupId="a")
    // public void consumer(String message){
    // System.out.println("Consumer 1 received "+message);
    // }
    @PostMapping("/issueBook")
    public ResponseEntity<Object> issueBook(@RequestBody Issuer obj) throws InterruptedException{
        String isbn=String.valueOf(obj.getIsbn());
           kafka.send("produceisbn","Isbn",isbn);
           Consumer.waitForUpdate();
           System.out.println("isbn sent"+isbn);
            System.out.println("Customer id "+obj.getCustId());
            System.out.println("isbn "+obj.getIsbn());
            System.out.println("No of copies "+obj.getNo_of_Copies());
           Integer availablesize= Consumer.getAvailablesize();
           if(availablesize>obj.getNo_of_Copies()){
            //issue book and save the cust id in db
            rep.save(obj);
            //sent the no of copies issued to the book ms
            String issuedcopyStr=String.valueOf(obj.getNo_of_Copies());
            kafka.send("issuedCopies",isbn,issuedcopyStr);
            
           }
           return ResponseEntity.ok(obj);

    }

    @GetMapping("/fetchAllCustDetails")
    public List<Issuer> fetchAllCustDetails(){
        return rep.findAll();

        
    }

    

}
