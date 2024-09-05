package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.darjedaar.inventorytracker.model.VendorDetails;

public interface VendorRepository extends CrudRepository<VendorDetails, Long>{

	
}
