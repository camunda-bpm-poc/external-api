package com.cgi.camunda.poc.service;

import com.cgi.camunda.poc.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private KafkaService kafkaService;

    public void postPayment(Account account) {
        kafkaService.sendMessage(account);
        log.info("Post Account payload to Kafka...");
    }

}
