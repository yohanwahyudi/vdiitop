package org.vdi.repository.dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.vdi.model.SummaryPerformance;
import org.vdi.repository.PerformanceSummaryRepository;
import org.vdi.repository.dao.PerformanceSummaryDAO;

@Repository("performanceSummaryDAO")
@Transactional
public class PerformanceSummaryDAOImpl implements PerformanceSummaryDAO{

	@Autowired
	PerformanceSummaryRepository repo;
	
	@Autowired
	EntityManager em;
	
	public PerformanceSummaryDAOImpl() {
		
	}

	@Override
	public void add(SummaryPerformance obj) {
		
		repo.save(obj);
		
	}

	@Override
	public void addAll(Collection<SummaryPerformance> col) {
		
		repo.saveAll(col);
		
	}

	@Override
	public void deleteEntity() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaDelete<SummaryPerformance> criteriaDelete = (CriteriaDelete<SummaryPerformance>) cb.createCriteriaDelete(SummaryPerformance.class)
				.from(SummaryPerformance.class); 
		
		em.createQuery(criteriaDelete).executeUpdate();
		
	}

	@Override
	public SummaryPerformance getPerformanceByWeek(String period, String category, int week) {
		return repo.getPerformanceByWeek(period, category, week);
	}

	@Override
	public SummaryPerformance getPerformanceByMonth(String period, String category, int month) {
		return repo.getPerformanceByMonth(period, category, month);
	}

	@Override
	public SummaryPerformance getPerformanceByShift(String period, String category) {
		return repo.getPerformanceByShift(period, category);
	}
	
	
	
}
