package com.darjedaar.inventorytracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.darjedaar.inventorytracker.model.LeaveTracker;

public interface LeaveTrackerRepository extends CrudRepository<LeaveTracker, Long> {

	@Query("SELECT lt FROM LeaveTracker lt WHERE lt.employee.id = :employeeId AND " +
	           "lt.appliedDate >= :startOfMonth AND lt.appliedDate <= :endOfMonth")
	    List<LeaveTracker> findLeavesByEmployeeInCurrentMonth(@Param("employeeId") Long employeeId, 
	                                                          @Param("startOfMonth") Date startOfMonth, 
	                                                          @Param("endOfMonth") Date endOfMonth);
}
