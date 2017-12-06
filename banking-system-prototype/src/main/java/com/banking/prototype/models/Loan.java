package com.banking.prototype.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name = "T_LOAN")
@Entity
public class Loan {

    @Id
    @GeneratedValue
    private long id;
    private long amount;
    private double interestRate;
    private long bankAccountId;
    private long userId;
    //total months of payback
    private int paybackPeriod;

    //payback amount every month
    private double paybackAmount;

//    private PaymentInfo info;

    public Loan(long amount, double interestRate, long bankAccountId, long userId, int paybackPeriod, double paybackAmount) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.bankAccountId = bankAccountId;
        this.userId = userId;
        this.paybackPeriod = paybackPeriod;
        this.paybackAmount = paybackAmount;
    }
}
