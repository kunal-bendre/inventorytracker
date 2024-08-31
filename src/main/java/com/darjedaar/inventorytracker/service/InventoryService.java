package com.darjedaar.inventorytracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.InventoryItem;
import com.darjedaar.inventorytracker.model.PurchaseItem;
import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;
import com.darjedaar.inventorytracker.repository.InventoryRecordRepository;
import com.darjedaar.inventorytracker.utility.InventoryExcelUtility;

@Service
public class InventoryService {

	@Autowired
	private InventoryRecordRepository inventoryRepository;

	@Autowired
	private InventoryExcelUtility inventoryExcelUtility;

	public void updateInventoryExcel(List<InventoryItem> inventoryRecords) throws Exception {
		inventoryExcelUtility.updateInventoryExcel(inventoryRecords);
	}

	public List<InventoryItem> updateInventoryOnUsage(List<InventoryItem> inventoryItemList) {
		List<InventoryItem> updatedInventoryList = new ArrayList<InventoryItem>();
		for (InventoryItem inventoryItem : inventoryItemList) {
			InventoryItem itemInInventory = inventoryRepository.findByDateAndName(inventoryItem.getDate(),
					inventoryItem.getName());
			itemInInventory
					.setTotalAvailableStock(itemInInventory.getTotalAvailableStock() - inventoryItem.getTotalUsage());
			itemInInventory.setTotalUsage(inventoryItem.getTotalUsage());
			updatedInventoryList.add(inventoryRepository.save(itemInInventory));
		}
		
		return updatedInventoryList;
	}

	public List<InventoryItem> updateInventoryOnPurchase(List<PurchaseOrderRecord> purchaseOrders) {
		List<InventoryItem> updatedInventoryList = new ArrayList<InventoryItem>();
		for (PurchaseOrderRecord purchaseOrder : purchaseOrders) {
			List<PurchaseItem> purchaseItems = purchaseOrder.getPurchaseItem();

			for (PurchaseItem item : purchaseItems) {
				InventoryItem inventoryItem = inventoryRepository.findByDateAndName(purchaseOrder.getDate(),
						item.getName());

				if (inventoryItem != null) {
					inventoryItem.setTotalAvailableStock(inventoryItem.getTotalAvailableStock() + item.getQuantity());
					updatedInventoryList.add(inventoryRepository.save(inventoryItem));
				} else {
					InventoryItem newPurchaseForDate = new InventoryItem();
					newPurchaseForDate.setDate(purchaseOrder.getDate());
					newPurchaseForDate.setName(item.getName());
					newPurchaseForDate.setTotalAvailableStock(item.getQuantity());
					newPurchaseForDate.setTotalUsage(0);
					updatedInventoryList.add(inventoryRepository.save(newPurchaseForDate));
				}
			}

		}
		
		return updatedInventoryList;
	}

	public List<InventoryItem> getInventoryByDate(Date date) {
		return inventoryRepository.findByDate(date);
	}

}
