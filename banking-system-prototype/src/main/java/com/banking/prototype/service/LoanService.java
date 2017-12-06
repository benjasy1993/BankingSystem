package com.banking.prototype.service;


import com.banking.prototype.models.Loan;
import com.banking.prototype.models.LoanProduct;
import com.banking.prototype.models.LoanApplication;
import com.banking.prototype.models.LoanApplicationStatus;
import com.banking.prototype.repositories.LoanApplicationRepository;
import com.banking.prototype.repositories.LoanRepository;
import com.banking.prototype.repositories.LoanProductRepository;
import oracle.jrockit.jfr.openmbean.ProducerDescriptorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LoanService {

    private static final double INTEREST_RATE = 0.10;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanApplicationRepository applicationRepository;

    @Autowired
    private LoanProductRepository loanProductRepository;

    public void submitLoanApplication(LoanApplication application) {
        applicationRepository.save(application);
    }

    public List<LoanApplication> listAllApplications() {
        return applicationRepository.findAll();
    }

    public LoanApplication getLoanApplicationByUserId(long userId) {
        return applicationRepository.findByUserId(userId);
    }

    public void deleteApplication(long applicationId) {
        applicationRepository.delete(applicationId);
    }

    @Transactional
    public void approveLoanApplication(long userId) {
        LoanApplication application = applicationRepository.findByUserId(userId);

        if (application.getStatus() == LoanApplicationStatus.PENDING) {
            application.setStatus(LoanApplicationStatus.APPROVED);
        }

        double paybackAmount = calculatePaybackAmount(
                application.getAmount(),
                INTEREST_RATE,
                application.getPaybackPeriod());

        Loan loan = new Loan(
                application.getAmount(),
                INTEREST_RATE,
                application.getBankAccountId(),
                application.getUserId(),
                application.getPaybackPeriod(),
                paybackAmount);

        loanRepository.save(loan);
        application.setLoanId(loan.getId());
        applicationRepository.save(application);

    }

//    public void rejectLoanApplication(long userId) {
//        LoanApplication application = applicationRepository.findByUserId(userId);
//
//
//    }

    private double calculatePaybackAmount(long amount, double interestRate, int paybackPeriod) {
        return amount/paybackPeriod * 1.2;
    }


    public LoanProduct getLoanProductByLoanProdId(long prodId) {
        LoanProduct item = loanProductRepository.getOne(prodId);
        return item;
    }

    public LoanProduct getLoanProductByProdName(String prodName) {
        //return loanProductRepository.getLoanProductByProdName(loanProdName);
        LoanProduct result = null;
        List<LoanProduct> list = loanProductRepository.findAll();
        for(LoanProduct i : list){
            if(i.getProdName().equals(prodName)){
                result = i;
            }
        }
        return result;
    }

    public void submitLoanProduct(LoanProduct loanProd) {
        System.out.println("loanprod"+ loanProd);
        loanProductRepository.save(loanProd);
    }
    public void deleteLoanProdAll() {
        loanProductRepository.deleteAll();
    }
    public List<LoanProduct> listAllLoanProd(){
        List<LoanProduct> list = loanProductRepository.findAll();
        return list;
    }
}
