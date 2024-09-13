package com.darjedaar.inventorytracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.darjedaar.inventorytracker.model.SaleRecord;

public interface SalesRecordRepository extends CrudRepository<SaleRecord, Long> {

	@Query("SELECT s FROM SaleRecord s WHERE s.date BETWEEN :startDate AND :endDate")
	List<SaleRecord> findSalesBetweenDates(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	@Query("SELECT SUM(s.totalIncome) FROM SaleRecord s WHERE s.date BETWEEN :startDate AND :endDate")
	Double sumTotalIncomeBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
