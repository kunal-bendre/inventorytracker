package com.darjedaar.inventorytracker.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "SaleRecord")
public class SaleRecord implements Serializable {

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
    @JoinColumn(name = "menuitem_id", referencedColumnName = "id")
	private MenuItem menuItem;
	
	private Integer totalProduce;
	private Integer halfPlateSale;
	private Integer fullPlateSale;
	private Integer bucketSale;
	private Integer kgSale;
	private Integer wastage;
	private Integer totalExpectedSales;
	private Double totalActualSales;
	private Double salesDifference;
	
	@JsonIgnore
	private Double totalIncome;
	
	private String comment;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItemName(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public Integer getTotalProduce() {
		return totalProduce;
	}

	public void setTotalProduce(Integer totalProduce) {
		this.totalProduce = totalProduce;
	}

	public Integer getHalfPlateSale() {
		return halfPlateSale;
	}

	public void setHalfPlateSale(Integer halfPlateSale) {
		this.halfPlateSale = halfPlateSale;
	}

	public Integer getFullPlateSale() {
		return fullPlateSale;
	}

	public void setFullPlateSale(Integer fullPlateSale) {
		this.fullPlateSale = fullPlateSale;
	}

	public Integer getBucketSale() {
		return bucketSale;
	}

	public void setBucketSale(Integer bucketSale) {
		this.bucketSale = bucketSale;
	}

	public Integer getKgSale() {
		return kgSale;
	}

	public void setKgSale(Integer kgSale) {
		this.kgSale = kgSale;
	}

	public Integer getWastage() {
		return wastage;
	}

	public void setWastage(Integer wastage) {
		this.wastage = wastage;
	}

	public Integer getTotalExpectedSales() {
		return totalExpectedSales;
	}

	public void setTotalExpectedSales(Integer totalExpectedSales) {
		this.totalExpectedSales = totalExpectedSales;
	}

	public Double getTotalActualSales() {
		return totalActualSales;
	}

	public void setTotalActualSales(Double totalActualSales) {
		this.totalActualSales = totalActualSales;
	}

	public Double getSalesDifference() {
		return salesDifference;
	}

	public void setSalesDifference(Double salesDifference) {
		this.salesDifference = salesDifference;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
