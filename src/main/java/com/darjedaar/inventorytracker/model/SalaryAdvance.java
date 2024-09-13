package com.darjedaar.inventorytracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "salary-advance")
public class SalaryAdvance {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Employee employee;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "UTC")
    private LocalDate date;

    @Column(nullable = false)
    private double totalSalary;

    @Column(nullable = false)
    private double advanceTaken;

    @Column(nullable = false)
    private double remainingSalary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public double getAdvanceTaken() {
		return advanceTaken;
	}

	public void setAdvanceTaken(double advanceTaken) {
		this.advanceTaken = advanceTaken;
	}

	public double getRemainingSalary() {
		return remainingSalary;
	}

	public void setRemainingSalary(double remainingSalary) {
		this.remainingSalary = remainingSalary;
	}

    // Getters and setters
}
