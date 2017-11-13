package com.banking.prototype.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "T_TRANSACTION")
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private long transactionId;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private BankAccount from;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private BankAccount to;

    private TransactionType type;

    private int amount;

    private Date date;

    private TransactionStatus status;

}
