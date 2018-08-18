package org.vdi.repository.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vdi.model.ServiceDeskVDI;
import org.vdi.repository.SDAgentRepository;
import org.vdi.repository.SDSummaryRepository;
import org.vdi.repository.dao.ServiceDeskDAO;

@Repository("serviceDeskDAO")
@Transactional
public class ServiceDeskDAOImpl implements ServiceDeskDAO{
	
	@Autowired
	SDSummaryRepository repo;
	
	@Autowired
	SDAgentRepository repoAgent;
	
	@Autowired
	EntityManager em;

	@Override
	public void add(ServiceDeskVDI obj) {
		
		repo.save(obj);
		
	}

	@Override
	public void addAll(Collection<ServiceDeskVDI> col) {
		
//		List<ServiceDeskVDI> sdList = new ArrayList<ServiceDeskVDI>();
//		sdList = castList(ServiceDeskVDI.class, col);
		
		repo.saveAll(col);
	}

	@Override
	public void deleteEntity() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<ServiceDeskVDI> queryDelete = (CriteriaDelete<ServiceDeskVDI>) cb.createCriteriaDelete(ServiceDeskVDI.class).from(ServiceDeskVDI.class);
		
		em.createQuery(queryDelete).executeUpdate();
		
	}

	@Override
	public List<Object[]> getSummaryByWeek(int week) {
		return repo.getSummaryByWeek(week);
	}

	@Override
	public List<Object[]> getSummaryByMonth(int month) {
		return repo.getSummaryByMonth(month);
	}

	@Override
	public List<Object[]> getSummaryByTime(String timeFrom, String timeTo) {
		return repo.getSummaryByHour(timeFrom, timeTo);
	}

	@Override
	public List<Object[]> getAgentPerformanceByWeek(int week) {
		return repoAgent.getAgentPerformanceByWeek(week);
	}

	@Override
	public List<Object[]> getAgentPerformanceByMonth(int month) {
		return repoAgent.getAgentPerformanceByMonth(month);
	}

	@Override
	public List<Object[]> getAgentPerformanceByTime(String timeFrom, String timeTo) {
		return repoAgent.getAgentPerformanceByTime(timeFrom, timeTo);
	}
	
//	public <T> List<T> castList(Class<? extends T> clazz, Collection<?> c){
//		List<T> r = new ArrayList<T>(c.size());
//		for(Object o : c) {
//			r.add(clazz.cast(o));
//		}
//		
//		return r;
//	}
	

}
