package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) representing the received message payload.
 * * IMPORTANT FOR DISTRIBUTED SYSTEMS:
 * 1. Package Name: Must be exactly "com.example.demo.models" to match the Producer's
 * "_type" header (com.example.demo.models.Email).
 * 2. Structure: Field names ("to", "body") must match the JSON keys sent by the Producer.
 */
@AllArgsConstructor
@NoArgsConstructor // Mandatory: Jackson uses this to instantiate the object before filling it with data
@Getter
@Setter
@ToString
public class Email {

    // Target recipient - extracted from the JSON "to" field
    private String to;

    // Content of the message - extracted from the JSON "body" field
    private String body;

}