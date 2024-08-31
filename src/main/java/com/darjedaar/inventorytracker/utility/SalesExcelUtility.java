package com.darjedaar.inventorytracker.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.darjedaar.inventorytracker.model.SaleRecord;

@Component
public class SalesExcelUtility extends ExcelUtility {

	private static final double BUCKETTOFULL = 4.5;
	private static final double HALFTOFULL = 0.75;
	private static final int KGTOFULL = 7;
	private final String PATH = "C:\\DarjedaarSales\\";
	private final String WORKBOOK_NAME = "darjedaar_sales_";

	private String getFileName(String year) {
		String fileName = PATH + WORKBOOK_NAME + year + ".xlsx";
		return fileName;
	}

	private void addHeaders(Sheet sheet) {
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Date");
		headerRow.createCell(1).setCellValue("MenuItem Name");
		headerRow.createCell(2).setCellValue("Total Produce");
		headerRow.createCell(3).setCellValue("Half Plate Sale");
		headerRow.createCell(4).setCellValue("Full Plate Sale");
		headerRow.createCell(5).setCellValue("Bucket Sale");
		headerRow.createCell(6).setCellValue("KG Sale");
		headerRow.createCell(7).setCellValue("Expected Sales");
		headerRow.createCell(8).setCellValue("Actual Sales");
		headerRow.createCell(9).setCellValue("Sales Difference");
		headerRow.createCell(10).setCellValue("Comment");
	}

	private List<SaleRecord> filterDuplicates(List<SaleRecord> inSaleRecords) {
		Set<SaleRecord> SaleRecordSet = new HashSet<>(inSaleRecords);
		List<SaleRecord> uniqueSaleRecords = new ArrayList<>(SaleRecordSet);
		return uniqueSaleRecords;
	}

	private Integer calculateExpectedSales(Integer totalProduce) {
		return totalProduce*KGTOFULL;
	}
	
	private double calculateActualSales(Integer half, Integer full, Integer bucket,Integer kg,Integer wastage) {
		return (half*HALFTOFULL) + full + (bucket*BUCKETTOFULL) + wastage;
	}

	public void updateSalesExcel(List<SaleRecord> SaleRecords) {

		List<SaleRecord> uniqueSaleRecords = filterDuplicates(SaleRecords);
		String year = calculateYear(SaleRecords.get(0).getDate());

		Workbook workbook = getWorkbook(year);
		Sheet currentSheet = getSheet(workbook, calculateMonth(SaleRecords.get(0).getDate()));
		addHeaders(currentSheet);

		int lastRow = currentSheet.getLastRowNum() + 1;
		if (lastRow > 1) {
			lastRow += 2;
		}
		
		for (SaleRecord saleRecord : uniqueSaleRecords) {
			Integer expectedSales = calculateExpectedSales(saleRecord.getTotalProduce());
			Double actualSales = calculateActualSales(saleRecord.getHalfPlateSale(),saleRecord.getFullPlateSale(),saleRecord.getBucketSale(),saleRecord.getKgSale(),saleRecord.getWastage());
			Double difference = expectedSales - actualSales;
			Row row = currentSheet.createRow(lastRow++);
			row.createCell(0).setCellValue(saleRecord.getDate().toString());
			row.createCell(1).setCellValue(saleRecord.getMenuItemName());
			row.createCell(2).setCellValue(saleRecord.getTotalProduce());
			row.createCell(3).setCellValue(saleRecord.getHalfPlateSale());
			row.createCell(4).setCellValue(saleRecord.getFullPlateSale());
			row.createCell(5).setCellValue(saleRecord.getBucketSale());
			row.createCell(6).setCellValue(saleRecord.getKgSale());
			row.createCell(7).setCellValue(saleRecord.getWastage());
			row.createCell(8).setCellValue(expectedSales);
			row.createCell(9).setCellValue(actualSales);
			row.createCell(10).setCellValue(difference);
			row.createCell(11).setCellValue(saleRecord.getComment());
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
