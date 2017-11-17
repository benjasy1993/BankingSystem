package com.banking.prototype.controllers;

import com.banking.prototype.models.Activity;
import com.banking.prototype.models.Transaction;
import com.banking.prototype.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping()
public class ActivityController {

    @Autowired
    private ActivityService service;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public void transfer(@RequestParam("fromAccountId") Long fromAccountId,
                         @RequestParam("toAccountId") Long toAccountId,
                         @RequestParam("amount") Double amount,
                         @RequestParam("scheduledDate") Date date) throws Exception{
        service.makeTransfer(fromAccountId, toAccountId, amount, date);
    }

    @RequestMapping(value = "/billpay", method = RequestMethod.GET)
    public void billPay(@RequestParam("fromAccountId") Long fromAccountId,
                        @RequestParam("toAccountId") Long toAccountId,
                        @RequestParam("amount") Double amount,
                        @RequestParam("scheduledDate") Date date) throws Exception{
        service.billPay(fromAccountId, toAccountId, amount, date);
    }

    @RequestMapping(value = "/activities/list")
    public Page<Activity> listActivitiesByAccount(@RequestParam("bankAccountId") Long bankAccountId,
                                         @RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE) Integer page,
                                         @RequestParam(value = "size", required = false, defaultValue = DEFAULT_SIZE) Integer size) {
        return service.listActivities(bankAccountId, new PageRequest(page, size));
    }

    @RequestMapping(value = "/activities/all")
    public List<Activity> listAllActivities() {
        return service.listAllActivities();
    }

}
