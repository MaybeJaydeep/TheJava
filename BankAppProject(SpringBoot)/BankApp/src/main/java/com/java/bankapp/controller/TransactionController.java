package com.java.bankapp.controller;

import com.java.bankapp.entity.Transaction;
import com.java.bankapp.service.TransactionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactionsByAccount(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }
}