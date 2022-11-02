package com.inputoutput;

import java.util.Scanner;

import com.inputoutput.EmployeePayrollService.InputOutputService;

public class InputOutput {

	public static void main(String[] args) {
		System.out.println("Welcome to input output program");
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Scanner scanner = new Scanner(System.in);
		employeePayrollService.readEmployeePayrollData(scanner);
		employeePayrollService.readEmployeePayrollData(scanner);
		employeePayrollService.writeEmployeePayrollData();
		String employeePayrollInformationFile = "E:\\bridgelabz\\practice\\nio_directory\\employee-payroll-information.txt";
		employeePayrollService.writeEmployeePayrollData(
				InputOutputService.FILE_IO, 
				employeePayrollInformationFile);
		employeePayrollService.printData(InputOutputService.FILE_IO);
		System.out.println("Employee payroll service has "+employeePayrollService.countEntries(InputOutputService.FILE_IO)+" entries");
	}

}
