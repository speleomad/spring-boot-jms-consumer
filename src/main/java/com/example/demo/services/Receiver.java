package com.example.demo.services;

import com.example.demo.models.Email;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Service responsible for consuming (receiving) messages from the JMS Broker.
 * In a distributed architecture, this class lives in the "Consumer" application.
 */
@Component
public class Receiver {

    /**
     * This method is automatically triggered whenever a new message arrives 
     * in the "mailbox" queue.
     * * @param email The message payload, automatically converted from JSON 
     * to an Email object by the 'myFactory' configuration.
     */
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        // Log the received object to the console.
        // The toString() method from Lombok in your Email model makes this output readable.
        System.out.println("Received <" + email + ">");
        
        // Logic to actually "process" the email (e.g., sending it via SMTP) 
        // would go here in a real application.
    }
}