package com.darjedaar.inventorytracker.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtility {
	
	protected Workbook getWorkbook(String fileName) {
		Workbook workbook = null;

		File file = new File(fileName);

		if (file.exists()) {
			try (FileInputStream fileIn = new FileInputStream(file)) {
				workbook = new XSSFWorkbook(fileIn);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			workbook = new XSSFWorkbook();
			try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
				workbook.write(fileOut);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return workbook;
	}

	protected Sheet getSheet(Workbook workbook, String month) {
		Sheet sheet = workbook.getSheet(month);
		if (sheet == null) {
			sheet = workbook.createSheet(month);
		}
		return sheet;
	}
	
	protected String calculateMonth(Date date) {
		SimpleDateFormat sdfm = new SimpleDateFormat("MMMM");
		String month = sdfm.format(date);
		return month;
	}
	
	protected String calculateYear(Date date) {
		SimpleDateFormat sdfy = new SimpleDateFormat("YYYY");
		String year = sdfy.format(date);
		return year;
	}
	
}
