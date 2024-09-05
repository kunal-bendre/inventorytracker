package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darjedaar.inventorytracker.model.MenuItem;

@Repository // Ensure this annotation is present
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
	// You can define custom query methods here if needed
}