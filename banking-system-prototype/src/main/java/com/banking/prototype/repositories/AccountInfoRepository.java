package com.banking.prototype.repositories;


import com.banking.prototype.models.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long>{

    AccountInfo getAccountInfoByUserId(int userId);
}
