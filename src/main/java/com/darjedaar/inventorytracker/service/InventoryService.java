package com.darjedaar.inventorytracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.darjedaar.inventorytracker.model.Consumables;
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
			if (!ObjectUtils.isEmpty(itemInInventory)) {
				itemInInventory.setTotalAvailableStock(
						itemInInventory.getTotalAvailableStock() - inventoryItem.getTotalUsage());
				itemInInventory.setTotalUsage(inventoryItem.getTotalUsage());
				updatedInventoryList.add(inventoryRepository.save(itemInInventory));
			}
		}

		return updatedInventoryList;
	}

	public List<InventoryItem> updateInventoryOnPurchase(PurchaseOrderRecord purchaseOrder) {
		List<InventoryItem> updatedInventoryList = new ArrayList<InventoryItem>();
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

		return updatedInventoryList;
	}

	public List<InventoryItem> getInventoryByDate(LocalDate date) {
		return inventoryRepository.findByDate(date);
	}

	public List<InventoryItem> transferInventory(LocalDate date) {
		List<InventoryItem> transferedInventory = new ArrayList<>();
		List<InventoryItem> remaningInventory = getInventoryByDate(date);
		LocalDate newDate = date.plusDays(1);

		for (InventoryItem remaningItem : remaningInventory) {
			InventoryItem nextDayRecord = new InventoryItem();
			nextDayRecord.setDate(newDate);
			nextDayRecord.setConsumable(remaningItem.getConsumable());
			nextDayRecord.setTotalAvailableStock(remaningItem.getTotalAvailableStock() - remaningItem.getTotalUsage());
			nextDayRecord.setTotalUsage(0);
			transferedInventory.add(inventoryRepository.save(nextDayRecord));
		}
		return transferedInventory;
	}

	public List<Consumables> getAllConsumables(LocalDate date) {
		List<Consumables> availableConsumablesInInventory = new ArrayList<>();
		List<InventoryItem> remaningInventory = getInventoryByDate(date);
		for(InventoryItem item : remaningInventory) {
			if(item.getTotalAvailableStock() > 0) {
				availableConsumablesInInventory.add(item.getConsumable());
			}
		}
		return availableConsumablesInInventory;
	}

}
