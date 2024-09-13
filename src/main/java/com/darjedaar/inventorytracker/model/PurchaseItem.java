package com.darjedaar.inventorytracker.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase-item")
public class PurchaseItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "consumable_id", referencedColumnName = "id")
	private Consumables consumable;
	
	private int unitRate;
	private int quantity;
	private double totalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_order_record_id")
	@JsonBackReference
	private PurchaseOrderRecord purchaseOrderRecord;

	public PurchaseItem() {
		super();
	}

	public PurchaseItem(Consumables consumable, int unitRate, int quantity) {
		this.setConsumable(consumable);
		this.unitRate = unitRate;
		this.quantity = quantity;
		this.totalPrice = unitRate * quantity;
	}
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Consumables getConsumable() {
		return consumable;
	}

	public void setConsumable(Consumables consumable) {
		this.consumable = consumable;
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

	public PurchaseOrderRecord getPurchaseOrderRecord() {
		return purchaseOrderRecord;
	}

	public void setPurchaseOrderRecord(PurchaseOrderRecord purchaseOrderRecord) {
		this.purchaseOrderRecord = purchaseOrderRecord;
	}
}
