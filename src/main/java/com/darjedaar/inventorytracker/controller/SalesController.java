package com.darjedaar.inventorytracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.MenuItem;
import com.darjedaar.inventorytracker.model.SaleRecord;
import com.darjedaar.inventorytracker.service.SalesService;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin("http://localhost:4200")
public class SalesController {

	@Autowired
	private SalesService salesService;
	
	@PostMapping("/saveSalesRecords")
	public void saveSalesRecords(@RequestBody List<SaleRecord> records) throws Exception {
		salesService.saveSalesRecord(records);
	}
	
	@PostMapping("/updateSalesExcel")
	public void updateSalesExcel(@RequestBody List<SaleRecord> records) throws Exception {
		salesService.updateSalesExcel(records);
	}
	
	@GetMapping("/sales-history")
	public ResponseEntity<List<SaleRecord>> getSalesHistory(
	        @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
	        @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate) {
	    List<SaleRecord> sales = salesService.getSalesByPeriod(startDate,endDate);
	    return ResponseEntity.ok(sales);
	}
	
	@PostMapping("/createMenuItem")
	public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
		return salesService.saveMenuItem(menuItem);
	}
	
	@GetMapping("/getAllMenuItem")
	public List<MenuItem> getAllMenuItem(){
		return salesService.getAllMenuItem();
	}
	
	@GetMapping("/getShowMenuItem")
	public List<MenuItem> getShowMenuItem(){
		return salesService.getShowMenuItem();
	}
	
	
}
