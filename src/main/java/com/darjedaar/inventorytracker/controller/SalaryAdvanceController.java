package com.darjedaar.inventorytracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.SalaryAdvance;
import com.darjedaar.inventorytracker.service.SalaryAdvanceService;

@RestController
@RequestMapping("/salary-advances")
public class SalaryAdvanceController {

	@Autowired
	private SalaryAdvanceService salaryAdvanceService;

	@PostMapping
	public SalaryAdvance createSalaryAdvance(@RequestBody SalaryAdvance salaryAdvance) {
		return salaryAdvanceService.saveSalaryAdvance(salaryAdvance);
	}

	@GetMapping("/{id}")
	public SalaryAdvance getSalaryAdvance(@PathVariable Long id) {
		return salaryAdvanceService.getSalaryAdvanceById(id);
	}
}
