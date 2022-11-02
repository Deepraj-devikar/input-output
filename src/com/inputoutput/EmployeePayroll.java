package com.inputoutput;

public class EmployeePayroll {
	private int employeeID;
	private String employeeName;
	private float salary;
	
	public int getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public float getSalary() {
		return salary;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee ID : "+employeeID+"\nEmployee Name : "+employeeName+"\nEmployee Salary : "+salary;
	}
		
}
