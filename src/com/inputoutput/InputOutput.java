package com.inputoutput;

import java.util.Scanner;

public class InputOutput {

	public static void main(String[] args) {
		System.out.println("Welcome to input output program");
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Scanner scanner = new Scanner(System.in);
		employeePayrollService.readEmployeePayrollData(scanner);
		employeePayrollService.writeEmployeePayrollData();
	}

}
