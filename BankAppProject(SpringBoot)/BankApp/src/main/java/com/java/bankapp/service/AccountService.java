package com.java.bankapp.service;

import com.java.bankapp.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccount(Long customerId);

    Account getAccount(Long accountId);

    void deposit(Long accountId, BigDecimal amount);

    void withdraw(Long accountId, BigDecimal amount);

    void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);

}