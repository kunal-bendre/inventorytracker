package com.darjedaar.inventorytracker.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Recipe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private List<ConsumableQuantity> consumables;

    // Constructors, getters, setters
    public Recipe() {}

    public Recipe(MenuItem menuItem, List<ConsumableQuantity> consumables) {
        this.menuItem = menuItem;
        this.consumables = consumables;
    }

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public List<ConsumableQuantity> getConsumables() {
		return consumables;
	}

	public void setConsumables(List<ConsumableQuantity> consumables) {
		this.consumables = consumables;
	}
}