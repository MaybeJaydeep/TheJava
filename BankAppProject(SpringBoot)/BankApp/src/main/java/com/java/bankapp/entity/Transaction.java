package com.java.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amount;

    private String description;

    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    @JsonBackReference(value = "account-transaction-from")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    @JsonBackReference(value = "account-transaction-to")
    private Account toAccount;
}
