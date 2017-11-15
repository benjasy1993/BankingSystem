package com.banking.prototype.models;

import javax.persistence.OneToOne;

public class CompanyAccountInfo {

    private int id;

    private String companyName;

    private String phoneNum;

    @OneToOne
    private BankAccount account;

    private String address;

}
