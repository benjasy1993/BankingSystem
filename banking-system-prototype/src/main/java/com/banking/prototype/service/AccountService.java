package com.banking.prototype.service;

import com.banking.prototype.models.AccountInfo;
import com.banking.prototype.models.BankAccount;
import com.banking.prototype.repositories.AccountInfoRepository;
import com.banking.prototype.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void createAccounts(int userId){
        AccountInfo accountInfo = new AccountInfo(userId);
        accountInfoRepository.save(accountInfo);
    }

    public AccountInfo getAccountInfo(int userId) {
        return accountInfoRepository.getAccountInfoByUserId(userId);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<BankAccount> getBankAccountsByUserId(int userId){
        return bankAccountRepository.findAllByInfo_UserId(userId);
    }
}
