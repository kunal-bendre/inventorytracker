package com.darjedaar.inventorytracker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.Record;

@Service
public class InventoryTrackerService {

	private final String PATH = "C:\\DarjedaarInventory\\";
	private final String WORKBOOK_NAME = "darjedaar_inventory_";

	private void addHeaders(Sheet sheet) {
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Date");
		headerRow.createCell(1).setCellValue("Item Name");
		headerRow.createCell(2).setCellValue("Opening");
		headerRow.createCell(3).setCellValue("Closing");
		headerRow.createCell(4).setCellValue("Comment");
	}

	private Workbook getWorkbook(String year) {
		String fileName = getFileName(year);
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

	private String getFileName(String year) {
		String fileName = PATH + WORKBOOK_NAME + year + ".xlsx";
		return fileName;
	}

	private Sheet getSheet(Workbook workbook, String month) {
		Sheet sheet = workbook.getSheet(month);
		if (sheet == null) {
			sheet = workbook.createSheet(month);
			addHeaders(sheet);
		}
		return sheet;
	}
	
	private List<Record> filterDuplicates(List<Record> inrecords) {
        Set<Record> recordSet = new HashSet<>(inrecords);
        List<Record> uniqueRecords = new ArrayList<>(recordSet);
		return uniqueRecords;
	}

	public void saveRecord(List<Record> records) throws Exception {

		List<Record> uniqueRecords = filterDuplicates(records);

		SimpleDateFormat sdfy = new SimpleDateFormat("YYYY");
		String year = sdfy.format(records.get(0).getDate());

		SimpleDateFormat sdfm = new SimpleDateFormat("MMMM");
		String month = sdfm.format(records.get(0).getDate());

		Workbook workbook = getWorkbook(year);
		Sheet currentSheet = getSheet(workbook, month);

		int lastRow = currentSheet.getLastRowNum() + 1;
		if (lastRow > 1) {
			lastRow += 2;
		}

		for (Record record : uniqueRecords) {
			Row row = currentSheet.createRow(lastRow++);
			row.createCell(0).setCellValue(record.getDate().toString());
			row.createCell(1).setCellValue(record.getItem().getName());
			row.createCell(2).setCellValue(record.getItem().getOpening());
			row.createCell(3).setCellValue(record.getItem().getClosing());
			row.createCell(4).setCellValue(record.getComment());
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