package com.banking.prototype.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Table(name = "T_LOANPRODUCT")
@Entity
@Data
@NoArgsConstructor
public class LoanProduct {

    @Id
    @GeneratedValue
    private long prodId;

    private String prodName;

    private String description;

    private Date startDate;

    private Date endDate;

    private int numOfRepaymentMax;

    private int numOfRepaymentDef;

    private int numOfRepaymentMin;

    private double interestRate;

    private double overdueCharge;

    public LoanProduct(String prodName, String description, Date startDate, Date endDate, int numOfRepaymentMax,
                       int numOfRepaymentDef, int numOfRepaymentMin, double interestRate, double overdueCharge) {
        this.prodName = prodName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numOfRepaymentDef = numOfRepaymentDef;
        this.numOfRepaymentMax = numOfRepaymentMax;
        this.numOfRepaymentMin = numOfRepaymentMin;
        this.interestRate = interestRate;
        this.overdueCharge = overdueCharge;
    }
}
