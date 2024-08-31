package com.darjedaar.inventorytracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrderRecord,Long> {

}
