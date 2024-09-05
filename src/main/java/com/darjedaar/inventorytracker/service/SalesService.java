package com.darjedaar.inventorytracker.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.MenuItem;
import com.darjedaar.inventorytracker.model.SaleRecord;
import com.darjedaar.inventorytracker.repository.MenuItemRepository;
import com.darjedaar.inventorytracker.repository.SalesRecordRepository;
import com.darjedaar.inventorytracker.utility.SalesExcelUtility;

@Service
public class SalesService {
	
	private static final double BUCKETTOFULL = 4.5;
	private static final double HALFTOFULL = 0.75;
	private static final int KGTOFULL = 7;
	
	@Autowired
	private SalesRecordRepository saleRecordRepository;
	
	@Autowired
	private SalesExcelUtility salesExcelUtility;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	private Integer calculateExpectedSales(Integer totalProduce) {
		return totalProduce*KGTOFULL;
	}
	
	private double calculateActualSales(Integer half, Integer full, Integer bucket,Integer kg,Integer wastage) {
		return (half*HALFTOFULL) + full + (bucket*BUCKETTOFULL) + wastage;
	}
	
	
	public Iterable<SaleRecord> saveSalesRecord(List<SaleRecord> saleRecords) {
		for(SaleRecord saleRecord : saleRecords) {
			Integer expectedSales = calculateExpectedSales(saleRecord.getTotalProduce());
			Double actualSales = calculateActualSales(saleRecord.getHalfPlateSale(),saleRecord.getFullPlateSale(),saleRecord.getBucketSale(),saleRecord.getKgSale(),saleRecord.getWastage());
			Double difference = expectedSales - actualSales;
			saleRecord.setTotalExpectedSales(expectedSales);
			saleRecord.setTotalActualSales(actualSales);
			saleRecord.setSalesDiffrence(difference);
		}
		
		return saleRecordRepository.saveAll(saleRecords);
	}
	
	public void updateSalesExcel(List<SaleRecord> salesRecord) {
		salesExcelUtility.updateSalesExcel(salesRecord);
	}

	public List<SaleRecord> getSalesByPeriod(Date startDate, Date endDate) {
		return saleRecordRepository.findSalesBetweenDates(startDate, endDate);
	}

	public MenuItem saveMenuItem(MenuItem menuItem) {
		return menuItemRepository.save(menuItem);
	}

	public List<MenuItem> getAllMenuItem() {
		return StreamSupport.stream(menuItemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

	}

}
