package com.darjedaar.inventorytracker.model;

import java.util.Date;
import java.util.Objects;

public class Record {
	
	private Date date;
    private Item item;
    private String comment;

    // Getters and Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(date, record.date) &&
               Objects.equals(item, record.item) &&
               Objects.equals(comment, record.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, item, comment);
    }
}
