package com.banking.prototype.models;

import com.banking.prototype.utils.AccountUtility;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_ACCOUNT_INFO")
@Data
@NoArgsConstructor
public class AccountInfo {

    @Id
    @Column(name = "USER_ID")
    private long userId;

    @OneToMany(mappedBy = "info", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    @Column(name = "CREATED_TIME")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date createdTime;

    //constructor for initialing three accounts
    public AccountInfo(int userId) {
        this.userId = userId;
        String routingNum = AccountUtility.generateAccountNum();
        bankAccounts = new ArrayList<BankAccount>();
        bankAccounts.add(new BankAccount(BankAccountType.CHECKING, routingNum, AccountUtility.generateAccountNum()));
        bankAccounts.add(new BankAccount(BankAccountType.SAVING, routingNum, AccountUtility.generateAccountNum()));
        bankAccounts.add(new BankAccount(BankAccountType.GROWING, routingNum, AccountUtility.generateAccountNum()));
        bankAccounts.forEach(account -> {
            account.setInfo(this);
        });
        this.createdTime = new Date();
    }

}
