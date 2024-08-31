package com.darjedaar.inventorytracker.model;


import java.io.Serializable;
import java.util.Date;
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
    private Date date;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // or EAGER depending on your use case
    @JoinColumn(name = "vendor_id")
    private VendorDetails vendor;
    
    @OneToMany(mappedBy = "purchaseOrderRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> purchaseItem;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  // or EAGER depending on your use case
    @JoinColumn(name = "invoice_id")   // column name in PurchaseOrderRecord table
    private Invoice invoice;

    public PurchaseOrderRecord(Date date, VendorDetails vendor, List<PurchaseItem> purchaseItem, Invoice invoice) {
        this.date = date;
        this.vendor = vendor;
        this.purchaseItem = purchaseItem;
        this.invoice = invoice;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
