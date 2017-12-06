package com.banking.prototype.controllers;

import com.banking.prototype.models.LoanApplication;
import com.banking.prototype.models.LoanProduct;
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

    @RequestMapping(value = "/product/byid", method = RequestMethod.GET)
    public LoanProduct getLoanProductByLoanProdId(@RequestParam("prodId") long prodId) {
        System.out.println(prodId);
        LoanProduct item = service.getLoanProductByLoanProdId(prodId);
        System.out.println(item);
        return item;
    }

    @RequestMapping(value = "/product/byname", method = RequestMethod.GET)
    public LoanProduct getLoanProductByProdName(@RequestParam("prodName") String prodName) {
        LoanProduct item = service.getLoanProductByProdName(prodName);
        return item;
    }
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public void submitLoanProd(@RequestBody LoanProduct loanProduct) {
        service.submitLoanProduct(loanProduct);
    }
    @RequestMapping(value = "/deleteall", method = RequestMethod.GET)
    public void deleteLoanProd(){
        service.deleteLoanProdAll();
    }

    @RequestMapping(value = "/listAllLoanProd", method = RequestMethod.GET)
    public List<LoanProduct> listAllLoanProd(){
        return service.listAllLoanProd();
    }
}
