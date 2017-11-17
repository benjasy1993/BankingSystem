package com.banking.prototype.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "T_TRANSACTION")
@Entity
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private long transactionId;

    private long fromBankAccountId;

    private long toBankAccountId;

    private TransactionType type;

    private double amount;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private java.sql.Date scheduledDate;

    private Date completedDate;

    private TransactionStatus status;

    public Transaction(long fromBankAccountId,
                       long toBankAccountId,
                       TransactionType type,
                       double amount,
                       java.sql.Date scheduledDate,
                       TransactionStatus status) {

        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.type = type;
        this.amount = amount;
        this.scheduledDate = scheduledDate;
        this.status = status;
    }

    public Transaction(long fromBankAccountId,
                       long toBankAccountId,
                       TransactionType type,
                       double amount,
                       java.sql.Date scheduledDate,
                       Date completedDate,
                       TransactionStatus status) {

        this.fromBankAccountId = fromBankAccountId;
        this.toBankAccountId = toBankAccountId;
        this.type = type;
        this.amount = amount;
        this.scheduledDate = scheduledDate;
        this.completedDate = completedDate;
        this.status = status;
    }
}
