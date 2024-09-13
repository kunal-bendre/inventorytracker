package com.darjedaar.inventorytracker.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.LeaveTracker;
import com.darjedaar.inventorytracker.repository.LeaveTrackerRepository;

@Service
public class LeaveTrackerService {

	@Autowired
	private LeaveTrackerRepository leaveTrackerRepository;

	public LeaveTracker save(LeaveTracker leaveTracker) {
		return leaveTrackerRepository.save(leaveTracker);
	}

	public LeaveTracker getLeaveTrackerById(Long id) {
		return leaveTrackerRepository.findById(id).orElse(null);
	}

	public List<LeaveTracker> getLeavesForCurrentMonth(Long employeeId) {
		LocalDate now = LocalDate.now();

		LocalDate startDate = now.withDayOfMonth(1);
		LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());

		return leaveTrackerRepository.findLeavesByEmployeeInCurrentMonth(employeeId, startDate, endDate);
	}
}
