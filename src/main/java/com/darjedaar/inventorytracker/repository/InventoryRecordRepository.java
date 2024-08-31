package com.darjedaar.inventorytracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.darjedaar.inventorytracker.model.InventoryItem;

public interface InventoryRecordRepository extends CrudRepository<InventoryItem, Long> {

	InventoryItem findByDateAndName(Date date, String itemName);
	
	List<InventoryItem> findByDate(Date date);
}