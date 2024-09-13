package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darjedaar.inventorytracker.model.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	// You can define custom query methods here if needed
}
