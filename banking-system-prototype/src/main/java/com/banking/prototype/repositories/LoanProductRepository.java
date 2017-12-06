package com.banking.prototype.repositories;

import com.banking.prototype.models.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {

    LoanProduct getLoanProductByProdName(String loanProdName);
}