package com.darjedaar.inventorytracker.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Invoice")
public class Invoice implements Serializable {
	
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
    
    @Column(name = "invoice_number")
    private String invoiceNumber;
    
    private double invoiceAmount;
    private String invoiceStatus;
    
    @Column(name = "invoiceImagePath")
    private String invoiceImagePath;
    
    private String invoicePhotoName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

	public String getInvoiceImagePath() {
		return invoiceImagePath;
	}

	public void setInvoiceImagePath(String invoiceImagePath) {
		this.invoiceImagePath = invoiceImagePath;
	}

	public String getInvoicePhotoName() {
		return invoicePhotoName;
	}

	public void setInvoicePhotoName(String invoicePhotoName) {
		this.invoicePhotoName = invoicePhotoName;
	}

}
