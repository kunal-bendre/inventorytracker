package com.darjedaar.inventorytracker.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class TransferInventoryRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private LocalDate date;

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
