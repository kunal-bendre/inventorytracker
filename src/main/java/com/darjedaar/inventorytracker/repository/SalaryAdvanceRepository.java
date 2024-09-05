package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.darjedaar.inventorytracker.model.SalaryAdvance;

public interface SalaryAdvanceRepository extends CrudRepository<SalaryAdvance, Long> {
    // Additional query methods can be defined here if needed
}
