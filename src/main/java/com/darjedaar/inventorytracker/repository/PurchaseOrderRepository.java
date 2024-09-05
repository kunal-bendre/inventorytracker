package com.darjedaar.inventorytracker.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.darjedaar.inventorytracker.model.PurchaseOrderRecord;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrderRecord,Long> {

	@Query("SELECT s FROM PurchaseOrderRecord s WHERE s.date BETWEEN :startDate AND :endDate")
	List<PurchaseOrderRecord> findPurchaseBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("SELECT p FROM PurchaseOrderRecord p WHERE p.invoice.invoiceNumber = :invoiceNumber")
	PurchaseOrderRecord findByInvoiceNumber(@Param("invoiceNumber") String invoiceNumber);

}
