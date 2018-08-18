package org.vdi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vdi.model.SummaryPerformance;


public interface PerformanceSummaryRepository extends CrudRepository<SummaryPerformance, Long>{

	@Query(value="select * from summary_performance WHERE  year(created_dt)=year(curdate()) AND "+
			"month(created_dt)=month(curdate())  AND week(curdate(),3) = :week AND period= :period AND category= :category;", nativeQuery=true)
	public SummaryPerformance getPerformanceByWeek(@Param ("period") String period, @Param("category") String category, 
			@Param("week") int week);
	
	@Query(value="select * from summary_performance WHERE  year(created_dt)=year(curdate()) AND "+
			"month(created_dt)= :month AND period= :period AND category= :category;", nativeQuery=true)
	public SummaryPerformance getPerformanceByMonth(@Param ("period") String period, @Param("category") String category, 
			@Param("month") int month);
	
	@Query(value="select * from summary_performance WHERE  date(created_dt)=curdate() AND "+
			"period= :period AND category= :category;", nativeQuery=true)
	public SummaryPerformance getPerformanceByShift(@Param ("period") String period, @Param("category") String category);

}
