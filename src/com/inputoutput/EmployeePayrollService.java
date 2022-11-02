package com.inputoutput;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeePayrollService {
	public enum InputOutputService {CONSOLE_IO, FILE_IO, DATABASE_IO, REST_IO};	
	private ArrayList<EmployeePayroll> employeePayrollData;
	
	public EmployeePayrollService() {
		employeePayrollData = new ArrayList<EmployeePayroll>();
	}
	
	public EmployeePayrollService(ArrayList<EmployeePayroll> employeePayrollData) {
		this.employeePayrollData = employeePayrollData;
	}

	public void writeEmployeePayrollData(InputOutputService inputOutputService, Object source) {
		switch (inputOutputService) {
		case CONSOLE_IO:
			writeEmployeePayrollData();
			break;
		case FILE_IO:
			new EmployeePayrollFIleIOService().writeData(employeePayrollData);
			break;
		default:
			break;
		}
	}
	
	public void printData(InputOutputService inputOutputService) {
		switch (inputOutputService) {
		case CONSOLE_IO:
			writeEmployeePayrollData();
			break;
		case FILE_IO:
			new EmployeePayrollFIleIOService().printData();
			break;
		default:
			break;	
		}
	}
	
	public long countEntries(InputOutputService inputOutputService) {
		switch (inputOutputService) {
		case CONSOLE_IO:
			return employeePayrollData.size();
		case FILE_IO:
			return new EmployeePayrollFIleIOService().countEntries();
		default:
			return 0;	
		}
	}
	
	public void readEmployeePayrollData(Scanner scanner) {
		EmployeePayroll EmployeePayroll = new EmployeePayroll();
		System.out.print("Enter employee ID : ");
		EmployeePayroll.setEmployeeID(readNumber(scanner));
		System.out.print("Enter employee name : ");
		EmployeePayroll.setEmployeeName(scanner.nextLine());
		System.out.print("Enter employee salary : ");
		EmployeePayroll.setSalary(readFloat(scanner));
		employeePayrollData.add(EmployeePayroll);
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
