package com.darjedaar.inventorytracker.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.AccountingHistory;

@Service
public class AccountingService {
	
	@Autowired
	private SalesService salesService;
	
	@Autowired
	private PurchaseService purchaseService;

	public AccountingHistory getAccounts(LocalDate startDate, LocalDate endDate) {
		AccountingHistory accountsRecord = new AccountingHistory();
		Double totalRevenue = salesService.sumTotalIncomeBetweenDates(startDate, endDate);
		Double totalRawMaterialCost = purchaseService.sumInvoiceAmountForPaidInvoicesBetweenDates(startDate, endDate);
		Double grossProfit = totalRevenue - totalRawMaterialCost;
		Double netProfit = grossProfit - 150000;
		Long percentage = Math.round((netProfit/ totalRevenue) * 100);
		String percentageString = percentage.toString() + "%";
		
		accountsRecord.setStartDate(startDate);
		accountsRecord.setEndDate(endDate);
		accountsRecord.setRevenue(totalRevenue);
		accountsRecord.setRawMaterialCost(totalRawMaterialCost);
		accountsRecord.setGrossProfit(totalRevenue - totalRawMaterialCost);
		accountsRecord.setFixedCost((double) 1500000);
		accountsRecord.setNetProfit(netProfit);
		accountsRecord.setPercentage(percentageString);
		
		return accountsRecord;
	}

}
