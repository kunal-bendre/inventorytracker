package com.darjedaar.inventorytracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu-item")
public class MenuItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String type;
	
	private String name;
	
	private Integer price;
	
	@Column(name="show_menuitem")
	@JsonIgnore
	private Boolean showMenuItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Boolean getShowMenuItem() {
		return showMenuItem;
	}

	public void setShowMenuItem(Boolean showMenuItem) {
		this.showMenuItem = showMenuItem;
	}
		
}
