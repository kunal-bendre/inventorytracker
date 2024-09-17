package com.darjedaar.inventorytracker.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.darjedaar.inventorytracker.model.AccountingHistory;

public interface AccountRepository extends CrudRepository<AccountingHistory, Long> {
	
	@Query("SELECT a FROM AccountingHistory a WHERE a.startDate >= :startDate AND a.endDate <= :endDate")
    AccountingHistory findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}