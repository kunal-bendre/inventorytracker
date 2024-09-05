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
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.Consumables;
import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;
import com.darjedaar.inventorytracker.model.VendorDetails;
import com.darjedaar.inventorytracker.service.PurchaseService;
import org.springframework.web.bind.annotation.RequestParam;


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
	
	@PostMapping("/addConsumable")
	public Consumables createConsumable(@RequestBody Consumables consumable) {
		return purchaseService.addConsumable(consumable);
	}

	@GetMapping("/getAllConsumables")
	public List<Consumables> getAllConsumables(){
		return purchaseService.getAllConsumables();
	}
	
	@PostMapping("/addNewVendor")
	public VendorDetails addNewVendor(@RequestBody VendorDetails vendorDetails) {
		return purchaseService.addNewVendor(vendorDetails);
	}
	
	@GetMapping("/getAllVendors")
	public List<VendorDetails> getAllVendors() {
		return purchaseService.getAllVendors();
	}
	
	@GetMapping("/getPurchaseHistory")
	public List<PurchaseOrderRecord> getPurchaseHistory(
	        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
	        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return purchaseService.getPurchaseHistory(startDate,endDate);
	}
	
	@PostMapping("/markOrderAsPaid")
	public PurchaseOrderRecord updatePurchaseOrder(@RequestParam("invoiceNumber") String invoiceNumber) {
		return purchaseService.markOrderAsPaid(invoiceNumber);
	}
	
	
}
