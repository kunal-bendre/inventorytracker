package com.darjedaar.inventorytracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.Recipe;
import com.darjedaar.inventorytracker.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;

	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

}
