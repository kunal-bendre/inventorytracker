package com.darjedaar.inventorytracker.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.InventoryItem;
import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;
import com.darjedaar.inventorytracker.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("http://localhost:4200")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/updateInventoryExcel")
	public void updateInventoryExcel(@RequestBody List<InventoryItem> records) throws Exception {
		inventoryService.updateInventoryExcel(records);
	}
	
	@PostMapping("/saveInventoryRecords")
	public List<InventoryItem> saveInventoryRecords(@RequestBody List<InventoryItem> records) throws Exception {
		return inventoryService.updateInventoryOnUsage(records);
	}
	
	@PostMapping("/update")
	public List<InventoryItem> updateInventory(@RequestBody List<PurchaseOrderRecord> purchaseOrders) {
		return inventoryService.updateInventoryOnPurchase(purchaseOrders);
	}
	
	@GetMapping("/getInventoryByDate")
	public List<InventoryItem> getInventoryByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
		return inventoryService.getInventoryByDate(date);
	}
}
