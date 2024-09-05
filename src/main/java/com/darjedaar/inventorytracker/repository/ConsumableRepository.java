package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darjedaar.inventorytracker.model.Consumables;

@Repository // Ensure this annotation is present
public interface ConsumableRepository extends CrudRepository<Consumables, Long> {
	// You can define custom query methods here if needed
}
