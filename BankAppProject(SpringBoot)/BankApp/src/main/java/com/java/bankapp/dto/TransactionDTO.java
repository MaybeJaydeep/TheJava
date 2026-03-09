package com.java.bankapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {

    private Long id;

    private BigDecimal amount;

    private String transactionType;

    private Long fromAccountId;

    private Long toAccountId;

    private LocalDateTime transactionDate;
}