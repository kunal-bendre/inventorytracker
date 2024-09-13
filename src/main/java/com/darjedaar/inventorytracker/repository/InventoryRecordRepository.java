package com.darjedaar.inventorytracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.darjedaar.inventorytracker.model.InventoryItem;

public interface InventoryRecordRepository extends CrudRepository<InventoryItem, Long> {

	@Query("SELECT p FROM InventoryItem p WHERE p.consumable.name = :itemName AND p.date = :date")
	InventoryItem findByDateAndName(LocalDate date, String itemName);
	
	List<InventoryItem> findByDate(LocalDate date);
}