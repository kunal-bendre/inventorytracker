package com.darjedaar.inventorytracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.ConsumableQuantity;
import com.darjedaar.inventorytracker.model.Recipe;
import com.darjedaar.inventorytracker.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;

	private Recipe convertToKgs(Recipe recipe) {
		for(ConsumableQuantity quantity : recipe.getConsumables()) {
			quantity.setQuantity(quantity.getQuantity() / 1000);
		}
		return recipe;
	}
	
	private List<ConsumableQuantity> convertToGrams(List<ConsumableQuantity> consumableList) {
		for(ConsumableQuantity quantity : consumableList) {
			quantity.setQuantity(quantity.getQuantity() * 1000);
		}
		return consumableList;
	}
	
	public Recipe save(Recipe recipe) {
		Recipe modifiedRecipe = convertToKgs(recipe);
		return recipeRepository.save(modifiedRecipe);
	}

	public List<ConsumableQuantity> getRecipe(String menuItemName) {
		List<ConsumableQuantity> consumableList = recipeRepository.findConsumablesByMenuItemName(menuItemName);
		List<ConsumableQuantity> modifiedRecipe = convertToGrams(consumableList);
		return modifiedRecipe;
	}

}
