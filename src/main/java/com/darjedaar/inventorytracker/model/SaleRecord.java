package com.darjedaar.inventorytracker.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("SaleRecord")
public class SaleRecord {

	@Id
	private String id;
	private Date date;
	private String menuItemName;
	private Integer totalProduce;
	private Integer halfPlateSale;
	private Integer fullPlateSale;
	private Integer bucketSale;
	private Integer kgSale;
	private String comment;

	public SaleRecord(String id, Date date, String menuItemName, Integer totalProduce, Integer halfPlateSale,
			Integer fullPlateSale, Integer bucketSale, Integer kgSale, String comment) {
		super();
		this.id = id;
		this.date = date;
		this.menuItemName = menuItemName;
		this.totalProduce = totalProduce;
		this.halfPlateSale = halfPlateSale;
		this.fullPlateSale = fullPlateSale;
		this.bucketSale = bucketSale;
		this.kgSale = kgSale;
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
