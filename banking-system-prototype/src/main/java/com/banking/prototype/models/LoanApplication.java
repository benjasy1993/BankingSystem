package com.banking.prototype.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "T_LOAN_APPLICATION")
public class LoanApplication {

    @Id
    private long id;
    private Date submittedDate;
    private String description;
    private LoanApplicationStatus status;
    private long amount;
    private long userId;
    private int paybackPeriod;
    private long loanId;
    private long bankAccountId;
    private Date decisionDate;

//    private Map<ApplicationMaterialType, String> materials;

    public LoanApplication() {
        this.submittedDate = new Date(new java.util.Date().getTime());
        this.status = LoanApplicationStatus.PENDING;
        //for applications that haven't been approved
        this.loanId = 0;
        this.decisionDate = new Date(0L);
    }
}
