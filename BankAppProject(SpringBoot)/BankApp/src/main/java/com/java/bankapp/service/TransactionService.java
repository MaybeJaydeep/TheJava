package com.java.bankapp.service;

import com.java.bankapp.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction recordTransaction(Transaction transaction);

    List<Transaction> getTransactionsByAccount(Long accountId);

}