package com.darjedaar.inventorytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.ConsumableQuantity;
import com.darjedaar.inventorytracker.model.Recipe;
import com.darjedaar.inventorytracker.service.RecipeService;

@RestController
@RequestMapping("/api/recipe")
@CrossOrigin("http://localhost:4200")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/createRecipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeService.save(recipe);
        return ResponseEntity.ok(savedRecipe);
    }
    
    @GetMapping("/getRecipe/{menuItemName}")
    public List<ConsumableQuantity> getRecipe(@PathVariable String menuItemName){
    	List<ConsumableQuantity> recipe = recipeService.getRecipe(menuItemName);
    	return recipe;
    }
}
