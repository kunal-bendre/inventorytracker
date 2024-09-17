package com.darjedaar.inventorytracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.darjedaar.inventorytracker.model.SalaryAdvance;

public interface SalaryAdvanceRepository extends CrudRepository<SalaryAdvance, Long> {

	@Query("SELECT s FROM SalaryAdvance s WHERE s.employee.id = :employeeId AND " +
			"s.date >= :startOfMonth AND s.date <= :endOfMonth")
	List<SalaryAdvance> findSalaryAdvancesByEmployeeId(@Param("employeeId") Long employeeId, 
            @Param("startOfMonth") LocalDate startOfMonth, 
            @Param("endOfMonth") LocalDate endOfMonth);
	
	@Query("SELECT COALESCE(SUM(sa.advanceTaken), 0) FROM SalaryAdvance sa WHERE sa.employee.id = :employeeId")
    double sumAdvanceTakenByEmployee(@Param("employeeId") Long employeeId);
}
