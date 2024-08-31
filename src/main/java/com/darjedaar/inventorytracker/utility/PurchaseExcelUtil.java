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

import com.darjedaar.inventorytracker.model.PurchaseItem;
import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;

@Component
public class PurchaseExcelUtil extends ExcelUtility {

	private final String PATH = "C:\\DarjedaarPurchase\\";
	private final String WORKBOOK_NAME = "darjedaar_purchase_";
	
	private String getFileName(String year) {
		String fileName = PATH + WORKBOOK_NAME + year + ".xlsx";
		return fileName;
	}
	
	private void addHeaders(Sheet sheet) {
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Date");
		headerRow.createCell(1).setCellValue("Item Name");
		headerRow.createCell(2).setCellValue("Quantity");
		headerRow.createCell(3).setCellValue("UnitRate");
		headerRow.createCell(4).setCellValue("Total Price");
		headerRow.createCell(5).setCellValue("Invoice Number");
		headerRow.createCell(6).setCellValue("Invoice Amount");
		headerRow.createCell(7).setCellValue("Invoice Status");
		headerRow.createCell(8).setCellValue("Vendor Name");
	}
	
	private List<PurchaseOrderRecord> filterDuplicates(List<PurchaseOrderRecord> inrecords) {
        Set<PurchaseOrderRecord> recordSet = new HashSet<>(inrecords);
        List<PurchaseOrderRecord> uniqueRecords = new ArrayList<>(recordSet);
		return uniqueRecords;
	}
	
	public void updatePurchaseOrderExcel(List<PurchaseOrderRecord> records) {
		
		List<PurchaseOrderRecord> uniqueRecords = filterDuplicates(records); 
		String year = calculateYear(records.get(0).getDate());
		
		Workbook workbook = getWorkbook(year);
		Sheet currentSheet = getSheet(workbook, calculateMonth(records.get(0).getDate()));
		addHeaders(currentSheet);
		
		int lastRow = currentSheet.getLastRowNum() + 1;
		if (lastRow > 1) {
			lastRow += 2;
		}

		for (PurchaseOrderRecord record : uniqueRecords) {
			for(PurchaseItem orderItem : record.getPurchaseItem()) {
				Row row = currentSheet.createRow(lastRow++);
				row.createCell(0).setCellValue(record.getDate().toString());
				row.createCell(1).setCellValue(orderItem.getName());
				row.createCell(2).setCellValue(orderItem.getQuantity());
				row.createCell(3).setCellValue(orderItem.getUnitRate());
				row.createCell(4).setCellValue(orderItem.getTotalPrice());
				row.createCell(5).setCellValue(record.getInvoice().getInvoiceNumber());
				row.createCell(6).setCellValue(record.getInvoice().getInvoiceAmount());
				row.createCell(7).setCellValue(record.getInvoice().getInvoiceStatus());
				row.createCell(8).setCellValue(record.getVendor().getName());
			}
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
