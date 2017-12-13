package com.banking.prototype.service;

import com.banking.prototype.models.AccountInfo;
import com.banking.prototype.models.BankAccount;
import com.banking.prototype.models.CompanyAccountInfo;
import com.banking.prototype.repositories.AccountInfoRepository;
import com.banking.prototype.repositories.BankAccountRepository;
import com.banking.prototype.repositories.CompanyAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CompanyAccountRepository companyAccountRepository;


    //if accounts is already created then do nothing
    public void createAccounts(long userId){
        if (!accountInfoRepository.exists(userId)) {
            AccountInfo accountInfo = new AccountInfo(userId);

            for (BankAccount account : accountInfo.getBankAccounts()) {
                account.setBalance(500);
            }
            accountInfoRepository.save(accountInfo);
        }
    }

    public AccountInfo getAccountInfo(long userId) {
        return accountInfoRepository.getAccountInfoByUserId(userId);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<BankAccount> getBankAccountsByUserId(long userId){
        return bankAccountRepository.findAllByInfo_UserId(userId);
    }

    public void addMoney(Long accountId, double amount) {
        BankAccount account = bankAccountRepository.getOne(accountId);
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);
    }

    public CompanyAccountInfo searchBillerBankAccount(String routingNum, String accountNum) {
        CompanyAccountInfo info = null;
        BankAccount account = bankAccountRepository.findFirstByRoutingNumAndAccountNum(routingNum, accountNum);
        if (account == null) {
            return info;
        }
        info = companyAccountRepository.findFirstByBankAccountId(account.getAccountId());
        if (info != null) {
            info.setBankAccount(account);
        }
        return info;
    }

    public void uploadCompanyAccountInfo(List<CompanyAccountInfo> list) {
        companyAccountRepository.save(list);
    }

    public List<CompanyAccountInfo> getAllBillers() {

        List<CompanyAccountInfo> list = companyAccountRepository.findAll();
        list.forEach(info -> {
            info.setBankAccount(bankAccountRepository.findOne(info.getBankAccountId()));
        });
        return list;
    }

    public void uploadBillers(List<CompanyAccountInfo> list) {
        companyAccountRepository.save(list);
    }

    public AccountInfo searchAccountInfo(String accountNum, String routingNum) {
        BankAccount bankAccount = bankAccountRepository.findFirstByRoutingNumAndAccountNum(routingNum, accountNum);
        AccountInfo info = accountInfoRepository.findAccountInfoByBankAccountsContaining(bankAccount);

        List<BankAccount> result = new ArrayList<>();
        result.add(bankAccount);
        info.setBankAccounts(result);

        return info;
    }


    public List<CompanyAccountInfo> searchByName(String input) {
        return companyAccountRepository.findAll();
    }

}
