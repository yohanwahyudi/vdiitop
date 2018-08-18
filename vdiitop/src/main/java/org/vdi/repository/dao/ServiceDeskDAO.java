package org.vdi.repository.dao;

import java.util.Collection;
import java.util.List;

import org.vdi.model.ServiceDeskVDI;

public interface ServiceDeskDAO {

	public void add(ServiceDeskVDI obj);
	public void addAll(Collection<ServiceDeskVDI> col);
	public void deleteEntity();
	
	public List<Object[]> getSummaryByWeek(int week);
	public List<Object[]> getSummaryByMonth(int month);
	//format time = hh:mm:ss
	public List<Object[]> getSummaryByTime(String timeFrom, String timeTo);
	
	public List<Object[]> getAgentPerformanceByWeek(int week);
	public List<Object[]> getAgentPerformanceByMonth(int month);
	//format time = hh:mm:ss
	public List<Object[]> getAgentPerformanceByTime(String timeFrom, String timeTo);
	
}
