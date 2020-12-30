package com.cgi.camunda.poc.repository;

import com.cgi.camunda.poc.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {
}
