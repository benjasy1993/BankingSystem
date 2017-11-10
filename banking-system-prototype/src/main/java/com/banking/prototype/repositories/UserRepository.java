package com.banking.prototype.repositories;


import com.banking.prototype.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
