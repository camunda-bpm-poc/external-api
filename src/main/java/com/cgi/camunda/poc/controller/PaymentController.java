package com.cgi.camunda.poc.controller;

import com.cgi.camunda.poc.model.Account;
import com.cgi.camunda.poc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-process")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/post-payment")
    public String postPayment(@RequestBody Account account) {
        paymentService.postPayment(account);
        return "Payment Posted";
    }

}
