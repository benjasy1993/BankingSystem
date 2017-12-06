package com.banking.prototype.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Table(name = "T_ACTIVITY")
@Entity
@Data
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue
    private long id;

    private long bankAccountId;

    private long transactionId;

    private String description;

    private Date date;

    private double balance;

    public Activity(long bankAccountId, long transactionId, String description, Date date, double balance) {
        this.bankAccountId = bankAccountId;
        this.transactionId = transactionId;
        this.description = description;
        this.date = date;
        this.balance = balance;
    }
}
