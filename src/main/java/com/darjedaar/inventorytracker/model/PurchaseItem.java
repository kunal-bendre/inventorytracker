package com.darjedaar.inventorytracker.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PurchaseItem")
public class PurchaseItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String name;
    private int unitRate;
    private int quantity;
    private double totalPrice;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_record_id")
    private PurchaseOrderRecord purchaseOrderRecord;

    public PurchaseItem(String name, int unitRate, int quantity) {
        this.name = name;
        this.unitRate = unitRate;
        this.quantity = quantity;
        this.totalPrice = unitRate * quantity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(int unitRate) {
        this.unitRate = unitRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

