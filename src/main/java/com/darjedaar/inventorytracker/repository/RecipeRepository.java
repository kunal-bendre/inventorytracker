package com.darjedaar.inventorytracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.darjedaar.inventorytracker.model.ConsumableQuantity;
import com.darjedaar.inventorytracker.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
	@Query("SELECT cq FROM Recipe r JOIN r.consumables cq WHERE r.menuItem.name = :menuItemName")
	List<ConsumableQuantity> findConsumablesByMenuItemName(@Param("menuItemName") String menuItemName);

}
