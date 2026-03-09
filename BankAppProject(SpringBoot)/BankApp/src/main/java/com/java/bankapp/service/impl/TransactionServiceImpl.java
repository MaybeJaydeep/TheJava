package com.java.bankapp.service.impl;

import com.java.bankapp.entity.Transaction;
import com.java.bankapp.repository.TransactionRepository;
import com.java.bankapp.service.TransactionService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction recordTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {

        List<Transaction> sentTransactions =
                transactionRepository.findByFromAccountId(accountId);

        List<Transaction> receivedTransactions =
                transactionRepository.findByToAccountId(accountId);

        sentTransactions.addAll(receivedTransactions);

        return sentTransactions;
    }

}