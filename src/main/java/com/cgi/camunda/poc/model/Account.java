package com.cgi.camunda.poc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "Account")
@Data
public class Account {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    private String ssn;
    private String accountNumber;
    private String paymentAmount;
    private String paymentDate;
}
