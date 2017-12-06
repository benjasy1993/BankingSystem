package com.banking.prototype.controllers;

import com.banking.prototype.models.LoanApplication;
import com.banking.prototype.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService service;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public void submitLoanApplication(@RequestBody LoanApplication application) {
        service.submitLoanApplication(application);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteApplication(@RequestParam("applicationId") long applicationId) {
        service.deleteApplication(applicationId);
    }

    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public List<LoanApplication> listAllApplications() {
        return service.listAllApplications();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public LoanApplication getApplicationByUserId(@RequestParam("userId") long userId) {
        return service.getLoanApplicationByUserId(userId);
    }

    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public void approveApplication(long userId) {
        service.approveLoanApplication(userId);
    }

//    @RequestMapping(value = "/reject", method = RequestMethod.GET)
//    public void rejectApplication(long userId) {
//        service.rejectLoanApplication(userId);
//    }

}
