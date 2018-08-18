package org.vdi.repository.dao;

import java.util.Collection;

import org.vdi.model.SummaryPerformance;

public interface PerformanceSummaryDAO {
	
	public void add(SummaryPerformance obj);
	public void addAll(Collection<SummaryPerformance> col);
	public void deleteEntity();
	
	public SummaryPerformance getPerformanceByWeek(String period, String category, int week);
	public SummaryPerformance getPerformanceByMonth(String period, String category, int month);
	public SummaryPerformance getPerformanceByShift(String period, String category);
			
}
