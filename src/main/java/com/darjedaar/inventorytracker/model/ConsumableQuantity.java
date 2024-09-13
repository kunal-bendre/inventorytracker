package com.darjedaar.inventorytracker.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ConsumableQuantity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumable_id", nullable = false)
    private Consumables consumable;

    @Column(nullable = false)
    private double quantity;

    // Constructors, getters, setters
    public ConsumableQuantity() {}

    public ConsumableQuantity(Consumables consumable, double quantity) {
        this.consumable = consumable;
        this.quantity = quantity;
    }

	public Consumables getConsumable() {
		return consumable;
	}

	public void setConsumable(Consumables consumable) {
		this.consumable = consumable;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
    
    
}
