package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.darjedaar.inventorytracker.model.Record;

public interface RecordRepository extends CrudRepository<Record,String> {
	
}