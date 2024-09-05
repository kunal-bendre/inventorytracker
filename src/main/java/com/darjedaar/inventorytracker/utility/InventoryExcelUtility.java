package com.darjedaar.inventorytracker.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.darjedaar.inventorytracker.model.InventoryItem;

@Component
public class InventoryExcelUtility extends ExcelUtility {

	private final String PATH = "C:\\DarjedaarInventory\\";
	private final String WORKBOOK_NAME = "darjedaar_inventory_";
	
	private String getFileName(String year) {
		String fileName = PATH + WORKBOOK_NAME + year + ".xlsx";
		return fileName;
	}
	
	private void addHeaders(Sheet sheet) {
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Date");
		headerRow.createCell(1).setCellValue("Item Name");
		headerRow.createCell(2).setCellValue("Total Usage");
		headerRow.createCell(3).setCellValue("Available Stock");
	}
	
	private List<InventoryItem> filterDuplicates(List<InventoryItem> inrecords) {
        Set<InventoryItem> recordSet = new HashSet<>(inrecords);
        List<InventoryItem> uniqueRecords = new ArrayList<>(recordSet);
		return uniqueRecords;
	}
	
	public void updateInventoryExcel(List<InventoryItem> records) {
		
		List<InventoryItem> uniqueRecords = filterDuplicates(records); 
		String year = calculateYear(records.get(0).getDate());
		
		Workbook workbook = getWorkbook(year);
		Sheet currentSheet = getSheet(workbook, calculateMonth(records.get(0).getDate()));
		addHeaders(currentSheet);
		
		int lastRow = currentSheet.getLastRowNum() + 1;
		if (lastRow > 1) {
			lastRow += 2;
		}

		for (InventoryItem record : uniqueRecords) {
			Row row = currentSheet.createRow(lastRow++);
			row.createCell(0).setCellValue(record.getDate().toString());
			row.createCell(1).setCellValue(record.getConsumable().getName());
			row.createCell(2).setCellValue(record.getTotalUsage());
			row.createCell(3).setCellValue(record.getTotalAvailableStock());
		}

		try (FileOutputStream fileOut = new FileOutputStream(getFileName(year))) {
			workbook.write(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
}
