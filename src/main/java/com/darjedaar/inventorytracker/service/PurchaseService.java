package com.darjedaar.inventorytracker.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.darjedaar.inventorytracker.model.Consumables;
import com.darjedaar.inventorytracker.model.InventoryItem;
import com.darjedaar.inventorytracker.model.Invoice;
import com.darjedaar.inventorytracker.model.PurchaseItem;
import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;
import com.darjedaar.inventorytracker.model.VendorDetails;
import com.darjedaar.inventorytracker.repository.ConsumableRepository;
import com.darjedaar.inventorytracker.repository.PurchaseOrderRepository;
import com.darjedaar.inventorytracker.repository.VendorRepository;
import com.darjedaar.inventorytracker.utility.PurchaseExcelUtil;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private PurchaseExcelUtil purchaseExcelUtil;

	final String uri = "http://localhost:8081/darjedaar/api/inventory/update";

	@Autowired
	private ConsumableRepository consumableRepository;

	public void updatePurchaseExcel(List<PurchaseOrderRecord> records) {
		purchaseExcelUtil.updatePurchaseOrderExcel(records);
	}

	@SuppressWarnings("unchecked")
	private List<InventoryItem> updateInventory(List<PurchaseOrderRecord> records) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(uri, records, List.class);
	}

	public List<InventoryItem> savePurchaseOrderRecords(List<PurchaseOrderRecord> records) {
		for (PurchaseOrderRecord record : records) {
			for (PurchaseItem item : record.getPurchaseItem()) {
				item.setPurchaseOrderRecord(record); // Maintain the bidirectional relationship
			}
			purchaseOrderRepository.save(record); // Save the purchase order record (cascade will save items)
		}
		return updateInventory(records);
	}

	public Consumables addConsumable(Consumables consumable) {
		return consumableRepository.save(consumable);
	}

	public List<Consumables> getAllConsumables() {
		return StreamSupport.stream(consumableRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public VendorDetails addNewVendor(VendorDetails vendor) {
		return vendorRepository.save(vendor);
	}

	public List<VendorDetails> getAllVendors() {
		return StreamSupport.stream(vendorRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<PurchaseOrderRecord> getPurchaseHistory(Date startDate, Date endDate) {
		return purchaseOrderRepository.findPurchaseBetweenDates(startDate, endDate);
	}

	public PurchaseOrderRecord markOrderAsPaid(String invoiceNumber) {
		PurchaseOrderRecord purchaseOrder = purchaseOrderRepository.findByInvoiceNumber(invoiceNumber);
		Invoice invoice = purchaseOrder.getInvoice();
		invoice.setInvoiceStatus("paid");
		return purchaseOrderRepository.save(purchaseOrder);
	}

}
