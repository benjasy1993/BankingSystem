package com.banking.prototype.controllers;

import com.banking.prototype.models.AccountInfo;
import com.banking.prototype.models.BankAccount;
import com.banking.prototype.models.CompanyAccountInfo;
import com.banking.prototype.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    @Autowired
    private AccountService service;

    @RequestMapping(value = "/initiate", method = RequestMethod.GET)
    public void initiateAccounts(@RequestParam(name = "userId") long userId){
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

    @RequestMapping(value = "/billers/search", method = RequestMethod.GET)
    public CompanyAccountInfo getBillerAccount(@RequestParam(name = "routingNum") String routingNum,
                                        @RequestParam(name = "accountNum") String accountNum) {
        return service.searchBillerBankAccount(routingNum, accountNum);
    }

    @RequestMapping(value = "billers/searchByName", method = RequestMethod.GET)
    public List<CompanyAccountInfo> searchBillersByName(@RequestParam(name = "input") String input) {
        return service.searchByName(input);
    }

    @RequestMapping(value = "/billers/upload", method = RequestMethod.POST)
    public void uploadCompanyAccountInfo(@RequestBody List<CompanyAccountInfo> list) {
        service.uploadCompanyAccountInfo(list);
    }

//    @RequestMapping(value = "/billers/initiate", method = RequestMethod.GET)
//    public void billersInitiate() throws Exception {
//        service.initiateCompanyAccounts();
//    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public AccountInfo searchAccountInfoByAccountNumByRoutingNum(@RequestParam("routingNum") String routingNum,
                                                                 @RequestParam("accountNum") String accountNum) {
        return service.searchAccountInfo(accountNum, routingNum);
    }

}
