package com.darjedaar.inventorytracker.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "InventoryItem")
public class InventoryItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "UTC")
	private LocalDate date;
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "consumable_id", referencedColumnName = "id")
	private Consumables consumable;
	
    private int totalAvailableStock;
    private int totalUsage;
    
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getTotalAvailableStock() {
		return totalAvailableStock;
	}

	public void setTotalAvailableStock(int totalAvailableStock) {
		this.totalAvailableStock = totalAvailableStock;
	}

	public int getTotalUsage() {
		return totalUsage;
	}

	public void setTotalUsage(int totalUsage) {
		this.totalUsage = totalUsage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(consumable, date, id, totalAvailableStock, totalUsage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return Objects.equals(consumable, other.consumable) && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id) && totalAvailableStock == other.totalAvailableStock
				&& totalUsage == other.totalUsage;
	}

	
}
