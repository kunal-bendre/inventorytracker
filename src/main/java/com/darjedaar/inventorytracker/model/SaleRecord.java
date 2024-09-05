package com.darjedaar.inventorytracker.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

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
	private Date date;
	private String menuItemName;
	private Integer totalProduce;
	private Integer halfPlateSale;
	private Integer fullPlateSale;
	private Integer bucketSale;
	private Integer kgSale;
	private Integer wastage;
	private Integer totalExpectedSales;
	private Double totalActualSales;
	private Double salesDiffrence;
	private String comment;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
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

	public Double getSalesDiffrence() {
		return salesDiffrence;
	}

	public void setSalesDiffrence(Double salesDiffrence) {
		this.salesDiffrence = salesDiffrence;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bucketSale, comment, date, fullPlateSale, halfPlateSale, id, kgSale, menuItemName,
				totalProduce);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaleRecord other = (SaleRecord) obj;
		return Objects.equals(bucketSale, other.bucketSale) && Objects.equals(comment, other.comment)
				&& Objects.equals(date, other.date) && Objects.equals(fullPlateSale, other.fullPlateSale)
				&& Objects.equals(halfPlateSale, other.halfPlateSale) && Objects.equals(id, other.id)
				&& Objects.equals(kgSale, other.kgSale) && Objects.equals(menuItemName, other.menuItemName)
				&& Objects.equals(totalProduce, other.totalProduce);
	}
	
	

}
