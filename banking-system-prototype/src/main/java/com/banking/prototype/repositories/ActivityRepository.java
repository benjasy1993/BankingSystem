package com.banking.prototype.repositories;

import com.banking.prototype.models.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Page<Activity> getActivitiesByBankAccountId(Long bankAccountId, Pageable pageable);
}
