package com.darjedaar.inventorytracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.Record;
import com.darjedaar.inventorytracker.repository.RecordRepository;
import com.darjedaar.inventorytracker.utility.ExcelUtility;

@Service
public class InventoryTrackerService {
	
	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private ExcelUtility excelUtility;
	

	public void updateExcel(List<Record> records) throws Exception {
		excelUtility.updateExcel(records);
	}

	public Iterable<Record> saveRecord(List<Record> records) {
		return recordRepository.saveAll(records);
	}

}