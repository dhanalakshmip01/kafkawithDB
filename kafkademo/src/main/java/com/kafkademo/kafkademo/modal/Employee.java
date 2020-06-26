package com.kafkademo.kafkademo.modal;

public class Employee {

	private int employeeId;
	private String empName;
	private String designation;
	private int jobLevel;
	private Double salary;
	private int rating;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getJobLevel() {
		return jobLevel;
	}
	public void setJobLevel(int jobLevel) {
		this.jobLevel = jobLevel;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
