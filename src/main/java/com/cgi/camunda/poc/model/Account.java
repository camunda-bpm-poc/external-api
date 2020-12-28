package com.cgi.camunda.poc.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Account {
    private String ssn;
    private String accountNumber;
    private BigDecimal paymentAmount;
}
