package com.darjedaar.inventorytracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darjedaar.inventorytracker.model.SalaryAdvance;
import com.darjedaar.inventorytracker.repository.SalaryAdvanceRepository;

@Service
public class SalaryAdvanceService {

	@Autowired
	private SalaryAdvanceRepository salaryAdvanceRepository;

	public SalaryAdvance saveSalaryAdvance(SalaryAdvance salaryAdvance) {
        double totalAdvanceTakenSoFar = salaryAdvanceRepository.sumAdvanceTakenByEmployee(salaryAdvance.getEmployee().getEmployeeId());
        double totalAdvanceTakenIncludingCurrent = totalAdvanceTakenSoFar + salaryAdvance.getAdvanceTaken();
        double remainingSalary = salaryAdvance.getEmployee().getTotalSalary() - totalAdvanceTakenIncludingCurrent;
        salaryAdvance.setRemainingSalary(remainingSalary);
        return salaryAdvanceRepository.save(salaryAdvance);
	}

	public List<SalaryAdvance> getSalaryAdvanceById(Long id) {
		LocalDate now = LocalDate.now();

		LocalDate startDate = now.withDayOfMonth(1);
		LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());

		return salaryAdvanceRepository.findSalaryAdvancesByEmployeeId(id,startDate,endDate);
	}
}
