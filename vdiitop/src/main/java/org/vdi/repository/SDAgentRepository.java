package org.vdi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vdi.model.ServiceDeskVDI;

public interface SDAgentRepository extends CrudRepository<ServiceDeskVDI, Long>{

	@Query(value=""
			+ "SELECT" + 
			"	A.agent, IFNULL(C.achieved,0) AS achieved, IFNULL(D.missed,0) AS missed, IFNULL(B.total,0) AS total " + 
			"FROM " + 
			"(" + 
			"	SELECT " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " + 
			"	AND " + 
			"		month(sd.incident_start_date)=:month " + 
			"	group by incident_agent" + 
			")A " + 
			"LEFT JOIN " + 
			"(" + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS total, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " + 
			"	AND " + 
			"		month(sd.incident_start_date)=:month " + 
			"	group by incident_agent " + 
			"	" + 
			")B on A.agent=B.agent " + 
			"LEFT JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS achieved, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		sd.incident_sla_tto_passed='no' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " + 
			"	AND " + 
			"		month(sd.incident_start_date)=:month " + 
			"	group by incident_agent " + 
			")C ON A.agent=C.agent " + 
			"LEFT JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS missed, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		sd.incident_sla_tto_passed='yes' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " + 
			"	AND " + 
			"		month(sd.incident_start_date)=:month " + 
			"	group by incident_agent " + 
			") D on A.agent=D.agent;", nativeQuery=true)
	public List<Object[]> getAgentPerformanceByMonth(@Param("month") int month);
	
	@Query(value=""
			+ "SELECT" + 
			"	A.agent, IFNULL(C.achieved,0) AS achieved, IFNULL(D.missed,0) AS missed, IFNULL(B.total,0) AS total " + 
			"FROM " + 
			"(" + 
			"	SELECT " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " +
			"	AND " +
			"		month(sd.incident_start_date)=month(curdate()) " +
			"	AND " + 
			"		week(sd.incident_start_date,3)=:week " + 
			"	group by incident_agent" + 
			")A " + 
			"LEFT JOIN " + 
			"(" + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS total, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " + 
			"	AND " +
			"		month(sd.incident_start_date)=month(curdate()) " +
			"	AND " + 
			"		week(sd.incident_start_date,3)=:week " + 
			"	group by incident_agent " + 
			"	" + 
			")B on A.agent=B.agent " + 
			"LEFT JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS achieved, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		sd.incident_sla_tto_passed='no' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " + 
			"	AND " +
			"		month(sd.incident_start_date)=month(curdate()) " +
			"	AND " + 
			"		week(sd.incident_start_date,3)=:week " + 
			"	group by incident_agent " + 
			")C ON A.agent=C.agent " + 
			"LEFT JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS missed, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		sd.incident_sla_tto_passed='yes' " + 
			"	AND " + 
			"		year(sd.incident_start_date)=year(curdate()) " + 
			"	AND " +
			"		month(sd.incident_start_date)=month(curdate()) " +
			"	AND " + 
			"		week(sd.incident_start_date,3)=:week " + 
			"	group by incident_agent " + 
			") D on A.agent=D.agent;", nativeQuery=true)	
	public List<Object[]> getAgentPerformanceByWeek(@Param("week") int week);
	
	@Query(value=""
			+ "SELECT" + 
			"	A.agent, IFNULL(C.achieved,0) AS achieved, IFNULL(D.missed,0) AS missed, IFNULL(B.total,0) AS total " + 
			"FROM " + 
			"(" + 
			"	SELECT " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		time(sd.incident_start_date)=curdate() " + 
			"	AND " + 
			"		time(sd.incident_start_date) >= :timeFrom " +
			"	AND " + 
			"		time(sd.incident_start_date) <= :timeto " +
			"	group by incident_agent" + 
			")A " + 
			"LEFT JOIN " + 
			"(" + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS total, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		time(sd.incident_start_date)=curdate() " + 
			"	AND " + 
			"		time(sd.incident_start_date) >= :timeFrom " +
			"	AND " + 
			"		time(sd.incident_start_date) <= :timeto " +
			"	group by incident_agent " + 
			"	" + 
			")B on A.agent=B.agent " + 
			"LEFT JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS achieved, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		sd.incident_sla_tto_passed='no' " + 
			"	AND " + 
			"		time(sd.incident_start_date)=curdate() " + 
			"	AND " + 
			"		time(sd.incident_start_date) >= :timeFrom " +
			"	AND " + 
			"		time(sd.incident_start_date) <= :timeto " +
			"	group by incident_agent " + 
			")C ON A.agent=C.agent " + 
			"LEFT JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(sd.incident_ref) AS missed, " + 
			"		sd.incident_agent AS agent " + 
			"	FROM " + 
			"		staging_servicedesk sd " + 
			"	WHERE " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND " + 
			"		sd.scalar_previous_value in ('escalated_tto','new') " + 
			"	AND " + 
			"		sd.scalar_new_value = 'assigned' " + 
			"	AND " + 
			"		sd.incident_sla_tto_passed='yes' " + 
			"	AND " + 
			"		time(sd.incident_start_date)=curdate() " + 
			"	AND " + 
			"		time(sd.incident_start_date) >= :timeFrom " +
			"	AND " + 
			"		time(sd.incident_start_date) <= :timeto " +
			"	group by incident_agent " + 
			") D on A.agent=D.agent;", nativeQuery=true)
	public List<Object[]> getAgentPerformanceByTime(@Param("timeFrom") String timeFrom, @Param("timeTo") String timeto);
	
}
