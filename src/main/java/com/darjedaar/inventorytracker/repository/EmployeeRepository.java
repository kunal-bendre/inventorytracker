package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.darjedaar.inventorytracker.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    // Additional query methods can be defined here if needed
}
