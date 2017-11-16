package com.banking.prototype.service;

import com.banking.prototype.models.Activity;
import com.banking.prototype.models.BankAccount;
import com.banking.prototype.models.Transaction;
import com.banking.prototype.models.TransactionStatus;
import com.banking.prototype.models.TransactionType;
import com.banking.prototype.repositories.ActivityRepository;
import com.banking.prototype.repositories.BankAccountRepository;
import com.banking.prototype.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void makeTransfer(Long fromAccountId, Long toAccountId, Double amount, Date date) throws Exception{
        BankAccount from = bankAccountRepository.findOne(fromAccountId);
        BankAccount to = bankAccountRepository.findOne(toAccountId);

        Transaction transaction;
        TransactionType type = from.getRoutingNum().equals(to.getRoutingNum()) ? TransactionType.INTERNAL_TRANSFER : TransactionType.EXTERNAL_TRANSFER;

        if (date.after(today())) {
            TransactionStatus status = TransactionStatus.PENDING;
            transaction = new Transaction(fromAccountId, toAccountId, type, amount, date, status);
        } else {
            if (from.getBalance() >= amount) {
                from.setBalance(from.getBalance() - amount);
                to.setBalance(to.getBalance() + amount);
            } else {
                throw new Exception("Invalid Balance");
            }
            TransactionStatus status = TransactionStatus.COMPLETED;
            transaction = new Transaction(fromAccountId, toAccountId, type, amount, date, new java.util.Date(), status);
        }
        transactionRepository.save(transaction);

        //save activities for both accounts
        activityRepository.save(new Activity(from.getAccountId(), transaction.getTransactionId(), ));

    }

    public void billPay() {

    }

    public Page<Activity> listActivities(Long bankAccountId, Pageable pageable) {
        return activityRepository.getActivitiesByBankAccountId(bankAccountId, pageable);
    }

    private Date today() {
        return (Date) new java.util.Date();
    }

    private String generateDescription(Transaction transaction, ) {

    }

}
