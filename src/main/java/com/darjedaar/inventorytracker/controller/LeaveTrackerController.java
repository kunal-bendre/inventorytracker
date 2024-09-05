package com.darjedaar.inventorytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.Employee;
import com.darjedaar.inventorytracker.model.LeaveTracker;
import com.darjedaar.inventorytracker.repository.EmployeeRepository;
import com.darjedaar.inventorytracker.repository.LeaveTrackerRepository;
import com.darjedaar.inventorytracker.service.LeaveTrackerService;

@RestController
@RequestMapping("/api/leave-trackers")
@CrossOrigin("http://localhost:4200")
public class LeaveTrackerController {

	@Autowired
	private LeaveTrackerService leaveTrackerService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LeaveTrackerRepository leaveTrackerRepository;

	@PostMapping("/createLeave")
	public LeaveTracker createLeaveTracker(@RequestBody LeaveTracker leaveTracker) {
		Employee employee = leaveTracker.getEmployee();
		if (employee.getEmployeeId() == null) {
			employee = employeeRepository.save(employee);
			leaveTracker.setEmployee(employee);
		}

		// Now save the leave tracker
		return leaveTrackerRepository.save(leaveTracker);
	}

	@GetMapping("/{id}")
	public List<LeaveTracker> getLeaveTracker(@PathVariable Long id) {
		return leaveTrackerService.getLeavesForCurrentMonth(id);
	}
}
