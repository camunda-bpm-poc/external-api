package com.cgi.camunda.poc.controller;

import com.cgi.camunda.poc.exception.ResourceNotFoundException;
import com.cgi.camunda.poc.model.Account;
import com.cgi.camunda.poc.repository.AccountRepository;
import com.cgi.camunda.poc.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment-process")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity< Account > getAccountById(@PathVariable(value = "id") Long accountId)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/accounts")
    public Account createAccount( @RequestBody Account account) {
        account.setId(sequenceGeneratorService.generateSequence(Account.SEQUENCE_NAME));
        return accountRepository.save(account);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity < Account > updateAccount(@PathVariable(value = "id") Long accountId,
                                                      @RequestBody Account accountDetails) throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));

        account.setAccountNumber(accountDetails.getAccountNumber());
        account.setPaymentAmount(accountDetails.getPaymentAmount());
        account.setPaymentDate(accountDetails.getPaymentDate());
        account.setSsn(accountDetails.getSsn());
        final Account updatedAccount = accountRepository.save(account);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public Map< String, Boolean > deleteAccount(@PathVariable(value = "id") Long accountId)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));

        accountRepository.delete(account);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
