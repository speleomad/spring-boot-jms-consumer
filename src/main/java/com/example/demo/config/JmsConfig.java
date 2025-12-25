package com.example.demo.config;

import jakarta.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Configuration class for the Consumer application.
 * It sets up the infrastructure to receive and automatically deserialize JMS messages.
 */
@Configuration
public class JmsConfig {

    /**
     * The Container Factory responsible for managing the @JmsListener lifecycle.
     * It handles the connection to the broker and polls for new messages.
     */
    @Bean
    public JmsListenerContainerFactory<?> myFactory(
            ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        
        // 1. Automatic Conversion:
        // This line applies Spring Boot's default settings, ensuring that our 
        // 'jacksonJmsMessageConverter' is used to transform JSON back into an Email object.
        
        // 2. High Volume / Multi-threading:
        // This factory handles the "workers". If you receive 100 messages at once, 
        // this bean manages how they are queued and processed asynchronously 
        // without blocking your application.
        
        // 3. Connection Management:
        // It uses the connectionFactory to maintain the link with the Artemis broker.
        configurer.configure(factory, connectionFactory);
        
        return factory;
    }

    /**
     * Defines how incoming JMS messages are converted back into Java objects.
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        
        // Tells the converter that we are expecting a TEXT message (JSON string).
        converter.setTargetType(MessageType.TEXT);
        
        // The Consumer looks at the "_type" header in the incoming message.
        // It uses this value (e.g., "com.example.demo.models.Email") to determine
        // which class to instantiate when deserializing the JSON body.
        converter.setTypeIdPropertyName("_type");
        
        return converter;
    }
}