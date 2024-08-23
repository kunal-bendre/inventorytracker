package com.darjedaar.inventorytracker.model;

import java.util.Objects;

public class Item {
	
	private String name;
    private int opening;
    private int closing;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpening() {
        return opening;
    }

    public void setOpening(int opening) {
        this.opening = opening;
    }

    public int getClosing() {
        return closing;
    }

    public void setClosing(int closing) {
        this.closing = closing;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return opening == item.opening &&
               closing == item.closing &&
               Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, opening, closing);
    }
}
