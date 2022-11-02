package com.inputoutput;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeePayrollService {
	public enum inputOutputService {CONSOLE_IO, FILE_IO, DATABASE_IO, REST_IO};
	private ArrayList<EmployeePayrolll> employeePayrollData;
	
	public EmployeePayrollService() {
		employeePayrollData = new ArrayList<EmployeePayrolll>();
	}
	
	public EmployeePayrollService(ArrayList<EmployeePayrolll> employeePayrollData) {
		this.employeePayrollData = employeePayrollData;
	}

	public void readEmployeePayrollData(Scanner scanner) {
		EmployeePayrolll employeePayrolll = new EmployeePayrolll();
		System.out.print("Enter employee ID : ");
		employeePayrolll.setEmployeeID(readNumber(scanner));
		System.out.print("Enter employee name : ");
		employeePayrolll.setEmployeeName(scanner.nextLine());
		System.out.print("Enter employee salary : ");
		employeePayrolll.setSalary(readFloat(scanner));
		employeePayrollData.add(employeePayrolll);
	}

	private float readFloat(Scanner scanner) {
		float floatingNumber;
		while(true) {
			try {
				floatingNumber = scanner.nextFloat();
				break;
			}catch(InputMismatchException e) {
				System.out.println("Please enter number : ");
			}finally {
				scanner.nextLine();
			}
		}
		return floatingNumber;
	}

	private int readNumber(Scanner scanner) {
		int number;
		while(true) {
			try {
				number = scanner.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.out.println("Please enter number : ");
			}finally {
				scanner.nextLine();
			}
		}
		return number;
	}

	public void writeEmployeePayrollData() {
		System.out.println("\nWriting Employee payroll roaster to console\n"+employeePayrollData);
	}
	
}
