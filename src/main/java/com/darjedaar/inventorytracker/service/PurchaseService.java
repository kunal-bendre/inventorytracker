package com.darjedaar.inventorytracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.darjedaar.inventorytracker.model.InventoryItem;
import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;
import com.darjedaar.inventorytracker.repository.PurchaseOrderRepository;
import com.darjedaar.inventorytracker.utility.PurchaseExcelUtil;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private PurchaseExcelUtil purchaseExcelUtil;

	final String uri = "http://localhost:8081/darjedaar/api/inventory/update";

	public void updatePurchaseExcel(List<PurchaseOrderRecord> records) {
		purchaseExcelUtil.updatePurchaseOrderExcel(records);
	}

	@SuppressWarnings("unchecked")
	private List<InventoryItem> updateInventory(List<PurchaseOrderRecord> records) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(uri, records, List.class);
	}

	public List<InventoryItem> savePurchaseOrderRecords(List<PurchaseOrderRecord> records) {
		purchaseOrderRepository.saveAll(records);
		return updateInventory(records);
	}

}
