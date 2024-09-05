package com.darjedaar.inventorytracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.Employee;
import com.darjedaar.inventorytracker.model.SalaryAdvance;
import com.darjedaar.inventorytracker.repository.SalaryAdvanceRepository;

@Service
public class SalaryAdvanceService {

	@Autowired
	private SalaryAdvanceRepository salaryAdvanceRepository;

	@Autowired
	private EmployeeService employeeService;

	public SalaryAdvance saveSalaryAdvance(SalaryAdvance salaryAdvance) {
		Employee employee = employeeService.getEmployeeById(salaryAdvance.getEmployee().getEmployeeId());
		if (employee != null) {
			double remainingSalary = employee.getTotalSalary() - salaryAdvance.getAdvanceTaken();
			salaryAdvance.setRemainingSalary(remainingSalary);
			return salaryAdvanceRepository.save(salaryAdvance);
		}
		return null;
	}

	public SalaryAdvance getSalaryAdvanceById(Long id) {
		return salaryAdvanceRepository.findById(id).orElse(null);
	}
}
