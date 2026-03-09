package com.java.bankapp.controller;

import com.java.bankapp.entity.Account;
import com.java.bankapp.service.AccountService;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create/{customerId}")
    public Account createAccount(@PathVariable Long customerId) {
        return accountService.createAccount(customerId);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }

    @PostMapping("/deposit")
    public void deposit(@RequestParam Long accountId,
                        @RequestParam BigDecimal amount) {

        accountService.deposit(accountId, amount);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam Long accountId,
                         @RequestParam BigDecimal amount) {

        accountService.withdraw(accountId, amount);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam Long fromAccountId,
                         @RequestParam Long toAccountId,
                         @RequestParam BigDecimal amount) {

        accountService.transfer(fromAccountId, toAccountId, amount);
    }
}