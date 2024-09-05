package com.darjedaar.inventorytracker.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	private String name;
	
	private Date joiningDate;
	
	private int age;
	
	@Column(nullable = false, unique = true)
	private String aadharCardNumber;
	
	private double totalSalary;
	
	private String position;

	@OneToMany(mappedBy = "employee")
	private List<SalaryAdvance> salaryAdvances;
	
	@OneToMany(mappedBy = "employee")
	private List<LeaveTracker> leaves;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getAadharCardNumber() {
		return aadharCardNumber;
	}

	public void setAadharCardNumber(String aadharCardNumber) {
		this.aadharCardNumber = aadharCardNumber;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<SalaryAdvance> getSalaryAdvances() {
		return salaryAdvances;
	}

	public void setSalaryAdvances(List<SalaryAdvance> salaryAdvances) {
		this.salaryAdvances = salaryAdvances;
	}

	public List<LeaveTracker> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<LeaveTracker> leaves) {
		this.leaves = leaves;
	}

}
