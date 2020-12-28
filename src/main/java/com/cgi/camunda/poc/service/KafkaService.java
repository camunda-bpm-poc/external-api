package com.cgi.camunda.poc.service;

import com.cgi.camunda.poc.model.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    private static final String KAFKA_TOPIC = "ExternalPaymentTopic";
    private static final String KAFKA_GROUP = "group_id";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Account account) {
        log.info(String.format("==> Incoming Message ==> %s", account.toString()));
        log.info("*** Convert User Object to JSON String ***");
        ObjectMapper objectMapper = new ObjectMapper();
        String accountJsonObject = null;
        try {
            accountJsonObject = objectMapper.writeValueAsString(account);
        } catch (JsonProcessingException e) {
            log.error("Error when converting User Object to JSON String: {}", e);
        }
        kafkaTemplate.send(KAFKA_TOPIC, KAFKA_GROUP, accountJsonObject);
        log.info("Account Posted to Kafka ==> {}", accountJsonObject);
    }

}
