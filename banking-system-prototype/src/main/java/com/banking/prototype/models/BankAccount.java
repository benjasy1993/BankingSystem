package com.banking.prototype.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table(name = "T_BANK_ACCOUNT")
@Entity
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private long accountId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private AccountInfo info;

    @Column(name = "ACCOUNT_NUM")
    private String accountNum;

    @Column(name = "ROUTING_NUM")
    private String routingNum;

    @Column(name = "BALANCE")
    private double balance;

    @Column(name = "ACCOUNT_TYPE")
    private BankAccountType type;

    public BankAccount(BankAccountType type, String routingNum, String accountNum) {
        this.routingNum = routingNum;
        this.accountNum = accountNum;
        this.type = type;
        this.balance = 0L;
    }

}

