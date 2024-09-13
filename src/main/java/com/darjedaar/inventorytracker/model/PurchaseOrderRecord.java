package com.darjedaar.inventorytracker.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PurchaseOrderRecord")
public class PurchaseOrderRecord implements Serializable {
	
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
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)  // or EAGER depending on your use case
    @JoinColumn(name = "vendor_id")
    private VendorDetails vendor;
    
    @OneToMany(mappedBy = "purchaseOrderRecord", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    private List<PurchaseItem> purchaseItem;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)  // or EAGER depending on your use case
    @JoinColumn(name = "invoice_id")   // column name in PurchaseOrderRecord table
    private Invoice invoice;
    
    
    public PurchaseOrderRecord() {
		super();
	}

	public PurchaseOrderRecord(LocalDate date, VendorDetails vendor, List<PurchaseItem> purchaseItem, Invoice invoice) {
        this.date = date;
        this.vendor = vendor;
        this.purchaseItem = purchaseItem;
        this.invoice = invoice;
    }
    
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

    public VendorDetails getVendor() {
        return vendor;
    }

    public void setVendor(VendorDetails vendor) {
        this.vendor = vendor;
    }

    public List<PurchaseItem> getPurchaseItem() {
        return purchaseItem;
    }

    public void setPurchaseItem(List<PurchaseItem> purchaseItem) {
        this.purchaseItem = purchaseItem;
        for (PurchaseItem item : purchaseItem) {
            item.setPurchaseOrderRecord(this); // Set the reference back to PurchaseOrderRecord
        }
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
