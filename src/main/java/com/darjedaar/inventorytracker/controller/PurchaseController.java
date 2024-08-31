package com.darjedaar.inventorytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;
import com.darjedaar.inventorytracker.service.PurchaseService;

@RestController
@RequestMapping("/api/purchase")
@CrossOrigin("http://localhost:4200")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@PostMapping("/savePurchaseRecords")
	public void savePurchaseOrderRecords(@RequestBody List<PurchaseOrderRecord> records) throws Exception {
		purchaseService.savePurchaseOrderRecords(records);
	}
	
	@PostMapping("/updatePurchaseExcel")
	public void updatePurchaseExcel(@RequestBody List<PurchaseOrderRecord> records) throws Exception {
		purchaseService.updatePurchaseExcel(records);
	}

}
