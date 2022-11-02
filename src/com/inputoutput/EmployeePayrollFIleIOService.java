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
}
