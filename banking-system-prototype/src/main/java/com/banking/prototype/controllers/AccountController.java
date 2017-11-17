package com.banking.prototype.controllers;

import com.banking.prototype.models.AccountInfo;
import com.banking.prototype.models.BankAccount;
import com.banking.prototype.models.CompanyAccountInfo;
import com.banking.prototype.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    @Autowired
    private AccountService service;

    @RequestMapping(value = "/initiate", method = RequestMethod.GET)
    public void initiateAccounts(@RequestParam(name = "userId") int userId){
        service.createAccounts(userId);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public AccountInfo getAccountDetailsByUserId(@RequestParam(name = "userId") int userId){
        return service.getAccountInfo(userId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<BankAccount> getAllBankAccounts() {
        return service.getAllBankAccounts();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<BankAccount> getAllBankAccounts(@RequestParam(name = "userId") int userId) {
        return service.getBankAccountsByUserId(userId);
    }

    @RequestMapping(value = "/billers", method = RequestMethod.GET)
    public List<CompanyAccountInfo> getAllBillers() {
        return service.getAllBillers();
    }

    @RequestMapping(value = "/billers", method = RequestMethod.POST)
    public void uploadBillers(@RequestBody List<CompanyAccountInfo> list) {
        service.uploadBillers(list);
    }

    //for internal developers
    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public void initiateAccounts(@RequestParam(name = "accountId") Long accountId,
                                 @RequestParam(name = "amount") Double amount){
        service.addMoney(accountId, amount);
    }
}
