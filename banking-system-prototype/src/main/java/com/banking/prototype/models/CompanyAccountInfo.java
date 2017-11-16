package com.banking.prototype.models;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity
@Data
public class CompanyAccountInfo {

    @Id
    @GeneratedValue
    private long id;

    private String companyName;

    private String phoneNum;

    @Embedded
    private BankAccount account;

    private String address;

}
