package com.darjedaar.inventorytracker.service;

import java.util.ArrayList;
import java.util.Calendar;
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
					inventoryItem.getConsumable().getName());
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
						item.getConsumable().getName());

				if (inventoryItem != null) {
					inventoryItem.setTotalAvailableStock(inventoryItem.getTotalAvailableStock() + item.getQuantity());
					updatedInventoryList.add(inventoryRepository.save(inventoryItem));
				} else {
					InventoryItem newPurchaseForDate = new InventoryItem();
					newPurchaseForDate.setDate(purchaseOrder.getDate());
					newPurchaseForDate.setConsumable(item.getConsumable());
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

	public List<InventoryItem> transferInventory(Date date) {
		List<InventoryItem> transferedInventory = new ArrayList<>();
		List<InventoryItem> remaningInventory = getInventoryByDate(date);
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date newDate = calendar.getTime();
        
		for(InventoryItem remaningItem : remaningInventory) {
			InventoryItem nextDayRecord = new InventoryItem();
			nextDayRecord.setDate(newDate);
			nextDayRecord.setConsumable(remaningItem.getConsumable());
			nextDayRecord.setTotalAvailableStock(remaningItem.getTotalAvailableStock() - remaningItem.getTotalUsage());
			nextDayRecord.setTotalUsage(0);
			transferedInventory.add(inventoryRepository.save(nextDayRecord));
		}
		return transferedInventory;
	}

}
