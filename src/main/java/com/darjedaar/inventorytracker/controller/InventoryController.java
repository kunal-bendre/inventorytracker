package com.darjedaar.inventorytracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.darjedaar.inventorytracker.model.Record;
import com.darjedaar.inventorytracker.service.InventoryTrackerService;


@RestController
@CrossOrigin("http://localhost:8081")
public class InventoryController {
	
	@Autowired
	private InventoryTrackerService inventoryService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Hello", HttpStatus.CREATED);
	}
	
	@PostMapping("/saveRecords")
    public void saveRecords(@RequestBody List<Record> records) throws Exception {
		inventoryService.saveRecord(records);
	}

}
