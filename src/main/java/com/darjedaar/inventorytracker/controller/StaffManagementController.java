package com.darjedaar.inventorytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.Employee;
import com.darjedaar.inventorytracker.service.EmployeeService;

@RestController
@RequestMapping("/api/staffmanagement")
@CrossOrigin("http://localhost:4200")
public class StaffManagementController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/createemployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
	
	@GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}/salary")
    public void updateEmployeeSalary(@PathVariable Long id, @RequestParam double salary) {
        employeeService.updateEmployeeSalary(id, salary);
    }
}
