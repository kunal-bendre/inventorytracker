package com.darjedaar.inventorytracker.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.AccountingHistory;
import com.darjedaar.inventorytracker.service.AccountingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/accounting")
@CrossOrigin("http://localhost:4200")
public class AccountingController {
	
	@Autowired
	private AccountingService accountingService;

	@GetMapping("/getAccountsHistory")
	public AccountingHistory getAccountsHistory(
	        @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
	        @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
		return accountingService.getAccounts(startDate,endDate);
	}
	
}
