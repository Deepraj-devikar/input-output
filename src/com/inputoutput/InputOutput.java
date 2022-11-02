package com.inputoutput;

import com.inputoutput.EmployeePayrollService.InputOutputService;

public class InputOutput {

	public static void main(String[] args) {
		System.out.println("Welcome to input output program");
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeePayrollData();
		employeePayrollService.readEmployeePayrollData();
		employeePayrollService.writeEmployeePayrollData();
		employeePayrollService.writeEmployeePayrollData(InputOutputService.FILE_IO);
		employeePayrollService.printData(InputOutputService.FILE_IO);
		System.out.println("Employee payroll service has "+employeePayrollService.countEntries(InputOutputService.FILE_IO)+" entries");
		employeePayrollService.readEmployeePayrollData(InputOutputService.FILE_IO);
	}

}
