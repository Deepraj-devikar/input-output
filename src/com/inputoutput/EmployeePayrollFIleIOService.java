package com.inputoutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EmployeePayrollFIleIOService {
	public static String PAY_ROLL_FILE_NAME = "E:\\bridgelabz\\practice\\nio_directory\\employee-payroll-information.txt";
	
	public boolean isValidFilePath(Path filePath) {
		if(!Files.exists(filePath)) {
			try {
				Files.createFile(filePath);
				return true;
			} catch (IOException e) {
				return false;
			}
		} else {
			return true;
		}
	}
	
	public void writeData(ArrayList<EmployeePayroll> employeePayrollData) {
		Path filePath = Paths.get(PAY_ROLL_FILE_NAME);
		if(isValidFilePath(filePath)) {
			StringBuffer employeePayrollInformation = new StringBuffer();
			employeePayrollData.forEach(currentEmployeePayrollInformation -> {
				employeePayrollInformation.append(currentEmployeePayrollInformation.toString().concat("\n"));
			});
			try {
				Files.write(filePath, employeePayrollInformation.toString().getBytes());
				System.out.println(employeePayrollData.size()+" employees payroll information written to "+filePath.toString()+" file");
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public void printData() {
		Path filePath = Paths.get(PAY_ROLL_FILE_NAME);
		if(Files.exists(filePath)) {
			try {
				Files.lines(filePath)
				.forEach(System.out::println);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public long countEntries() {
		long entriesCounting = 0;
		Path filePath = Paths.get(PAY_ROLL_FILE_NAME);
		if(Files.exists(filePath)) {
			try {
				entriesCounting = Files.lines(filePath)
						.count();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return entriesCounting/3;
	}

	public void readData() {
		Path filePath = Paths.get(PAY_ROLL_FILE_NAME);
		ArrayList<String> employeeIDs = new ArrayList<String>();
		ArrayList<String> employeeNames = new ArrayList<String>();
		ArrayList<String> employeeSalaries = new ArrayList<String>();
		String employeeIDIdentifier = "Employee ID";
		String employeeNameIdentifier = "Employee Name";
		String employeeSalaryIdentifier = "Employee Salary";
		if(Files.exists(filePath)) {
			try {
				Files.lines(filePath)
				.forEach(line -> {
					if(line.startsWith(employeeIDIdentifier)) {
						employeeIDs.add(
								line.split(employeeIDIdentifier+" : ")[1].trim()
								);
					} else if(line.startsWith(employeeNameIdentifier)) {
						employeeNames.add(
								line.split(employeeNameIdentifier+" : ")[1].trim()
								);
					} else if(line.startsWith(employeeSalaryIdentifier)) {
						employeeSalaries.add(
								line.split(employeeSalaryIdentifier+" : ")[1].trim()
								);
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		ArrayList<EmployeePayroll> employeePayrollData = new ArrayList<EmployeePayroll>();
		long minimumData = employeeIDs.size();
		if(minimumData > employeeNames.size()) {
			minimumData = employeeNames.size();
		}
		if(minimumData > employeeSalaries.size()) {
			minimumData = employeeSalaries.size();
		}
		for(int i = 0; i < minimumData; i++) {
			EmployeePayroll tempEmployeePayrollData = new EmployeePayroll();
			tempEmployeePayrollData.setEmployeeID(Integer.parseInt(employeeIDs.get(i)));
			tempEmployeePayrollData.setEmployeeName(employeeNames.get(i));
			tempEmployeePayrollData.setSalary(Float.parseFloat(employeeSalaries.get(i)));
			employeePayrollData.add(tempEmployeePayrollData);
		}
		
		System.out.println("data found in "+filePath.toString()+" file : ");
		System.out.println(employeePayrollData);
	}
}
