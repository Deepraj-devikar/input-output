package com.inputoutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Assert;

public class EmployeePayrollService {
	public enum InputOutputService {CONSOLE_IO, FILE_IO, DATABASE_IO, REST_IO};	
	private ArrayList<EmployeePayrolll> employeePayrollData;
	
	public EmployeePayrollService() {
		employeePayrollData = new ArrayList<EmployeePayrolll>();
	}
	
	public EmployeePayrollService(ArrayList<EmployeePayrolll> employeePayrollData) {
		this.employeePayrollData = employeePayrollData;
	}

	public void writeEmployeePayrollData(InputOutputService inputOutputService, Object source) {
		switch (inputOutputService) {
		case CONSOLE_IO:
			writeEmployeePayrollData();
			break;
		case FILE_IO:
			if(source instanceof String) {
				Path filePath = Paths.get((String) source);
				if(!Files.exists(filePath)) {
					try {
						Files.createFile(filePath);
						writeEmployeePayrollData(filePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					writeEmployeePayrollData(filePath);
				}
			}
			break;
		default:
			break;
		}
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
	
	public void writeEmployeePayrollData(Path filePath) {
		StringBuffer employeePayrollInformation = new StringBuffer();
		employeePayrollData.forEach(currentEmployeePayrollInformation -> {
			employeePayrollInformation.append(currentEmployeePayrollInformation.toString().concat("\n"));
		});
		try {
			Files.write(filePath, employeePayrollInformation.toString().getBytes());
			System.out.println(employeePayrollData.size()+" employees payroll information written to "+filePath.toString()+" file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
