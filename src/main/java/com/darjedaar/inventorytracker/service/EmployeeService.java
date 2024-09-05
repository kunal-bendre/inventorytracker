package com.darjedaar.inventorytracker.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.Employee;
import com.darjedaar.inventorytracker.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public void updateEmployeeSalary(Long employeeId, double newSalary) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		if (employee != null) {
			employee.setTotalSalary(newSalary);
			employeeRepository.save(employee);
		}
	}

	public List<Employee> getAllEmployees() {
		return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
	}
}
