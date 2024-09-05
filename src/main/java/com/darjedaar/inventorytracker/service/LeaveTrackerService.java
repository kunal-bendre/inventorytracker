package com.darjedaar.inventorytracker.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.Employee;
import com.darjedaar.inventorytracker.model.LeaveTracker;
import com.darjedaar.inventorytracker.repository.EmployeeRepository;
import com.darjedaar.inventorytracker.repository.LeaveTrackerRepository;

@Service
public class LeaveTrackerService {

	@Autowired
	private LeaveTrackerRepository leaveTrackerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public LeaveTracker saveLeaveTracker(LeaveTracker leaveTracker) {
		Employee employee = leaveTracker.getEmployee();
		if (employee.getEmployeeId() == null) {
			employee = employeeRepository.save(employee);
			leaveTracker.setEmployee(employee);
		}

		return leaveTrackerRepository.save(leaveTracker);
	}

	public LeaveTracker getLeaveTrackerById(Long id) {
		return leaveTrackerRepository.findById(id).orElse(null);
	}

	public List<LeaveTracker> getLeavesForCurrentMonth(Long employeeId) {
		LocalDate now = LocalDate.now();

		LocalDate startOfMonth = now.withDayOfMonth(1);
		LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth());

		Date startDate = Date.from(startOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

		return leaveTrackerRepository.findLeavesByEmployeeInCurrentMonth(employeeId, startDate, endDate);
	}
}
