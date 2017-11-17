package com.banking.prototype.service;

import com.banking.prototype.models.AccountInfo;
import com.banking.prototype.models.BankAccount;
import com.banking.prototype.models.CompanyAccountInfo;
import com.banking.prototype.repositories.AccountInfoRepository;
import com.banking.prototype.repositories.BankAccountRepository;
import com.banking.prototype.repositories.CompanyAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CompanyAccountRepository companyAccountRepository;

    public void createAccounts(int userId){
        AccountInfo accountInfo = new AccountInfo(userId);
        accountInfoRepository.save(accountInfo);
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
}
