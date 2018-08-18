package org.vdi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.vdi.model.ServiceDeskVDI;

public interface SDSummaryRepository extends CrudRepository<ServiceDeskVDI, Long>{
	
	@Query(value=""
			+ "SELECT A.total, B.achieved, C.missed " + 
			"FROM  " + 
			"( " + 
			"	SELECT " + 
			"		count(incident_ref) as total " + 
			"    FROM " + 
			"		staging_servicedesk  " + 
			"	WHERE  " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND  " + 
			"		year(incident_start_date)=year(curdate())  " + 
			"	AND " + 
			"		month(incident_start_date)=month(curdate())  " + 
			"	AND  " + 
			"		week(incident_start_date)=:week " + 
			" " + 
			") A " + 
			"JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(incident_ref) as achieved " + 
			"    FROM " + 
			"		staging_servicedesk  " + 
			"	WHERE  " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND  " + 
			"		year(incident_start_date)=year(curdate())  " + 
			"	AND " + 
			"		month(incident_start_date)=month(curdate())  " + 
			"	AND  " + 
			"		week(incident_start_date)=:week " + 
			"	AND " + 
			"		incident_sla_tto_passed='no' " + 
			") B " + 
			"JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(incident_ref) as missed " + 
			"    FROM " + 
			"		staging_servicedesk  " + 
			"	WHERE  " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND  " + 
			"		year(incident_start_date)=year(curdate())  " + 
			"	AND " + 
			"		month(incident_start_date)=month(curdate())  " + 
			"	AND  " + 
			"		week(incident_start_date)=:week " + 
			"	AND " + 
			"		incident_sla_tto_passed='yes' " + 
			") C " + 
			"; " + 
			"", nativeQuery=true)
	public List<Object[]> getSummaryByWeek(@Param("week") int week);
	
	@Query(value=""
			+ "SELECT A.total, B.achieved, C.missed " + 
			"FROM  " + 
			"( " + 
			"	SELECT " + 
			"		count(incident_ref) as total " + 
			"    FROM " + 
			"		staging_servicedesk  " + 
			"	WHERE  " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND  " + 
			"		year(incident_start_date)=year(curdate())  " + 
			"	AND " + 
			"		month(incident_start_date)= :month  " + 
			" " + 
			") A " + 
			"JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(incident_ref) as achieved " + 
			"    FROM " + 
			"		staging_servicedesk  " + 
			"	WHERE  " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND  " + 
			"		year(incident_start_date)=year(curdate())  " + 
			"	AND " + 
			"		month(incident_start_date)= :month  " + 
			"	AND " + 
			"		incident_sla_tto_passed='no' " + 
			") B " + 
			"JOIN " + 
			"( " + 
			"	SELECT " + 
			"		count(incident_ref) as missed " + 
			"    FROM " + 
			"		staging_servicedesk  " + 
			"	WHERE  " + 
			"		incident_organization_name='Visionet International (OVO) PT' " + 
			"	AND  " + 
			"		year(incident_start_date)=year(curdate())  " + 
			"	AND " + 
			"		month(incident_start_date)= :month  " + 
			"	AND " + 
			"		incident_sla_tto_passed='yes' " + 
			") C " + 
			"; " +
			"", nativeQuery=true)
	public List<Object[]> getSummaryByMonth (@Param("month") int month);
	
	@Query(value=""
			+ "SELECT A.total, B.achieved, C.missed  " + 
			"			FROM   " + 
			"			(  " + 
			"				SELECT  " + 
			"					count(incident_ref) as total  " + 
			"			    FROM  " + 
			"					staging_servicedesk   " + 
			"				WHERE   " + 
			"					incident_organization_name='Visionet International (OVO) PT'  " + 
			"				AND   " + 
			"					date(incident_start_date)=curdate()   " + 
			"				AND " + 
			"					TIME(incident_start_date) >= :timeFrom  " + 
			"				AND " + 
			"					TIME(incident_start_date) <= :timeTo  " + 
			"			  " + 
			"			) A  " + 
			"			JOIN  " + 
			"			(  " + 
			"				SELECT  " + 
			"					count(incident_ref) as achieved  " + 
			"			    FROM  " + 
			"					staging_servicedesk   " + 
			"				WHERE   " + 
			"					incident_organization_name='Visionet International (OVO) PT'  " + 
			"				AND   " + 
			"					date(incident_start_date)=curdate()   " + 
			"				AND " + 
			"					TIME(incident_start_date) >= :timeFrom  " + 
			"				AND " + 
			"					TIME(incident_start_date) <= :timeTo   " + 
			"				AND  " + 
			"					incident_sla_tto_passed='no'  " + 
			"			) B  " + 
			"			JOIN  " + 
			"			(  " + 
			"				SELECT  " + 
			"					count(incident_ref) as missed  " + 
			"			    FROM  " + 
			"					staging_servicedesk   " + 
			"				WHERE   " + 
			"					incident_organization_name='Visionet International (OVO) PT'  " + 
			"				AND   " + 
			"					date(incident_start_date)=curdate()   " + 
			"				AND " + 
			"					TIME(incident_start_date) >= :timeFrom  " + 
			"				AND " + 
			"					TIME(incident_start_date) <= :timeTo    " + 
			"				AND  " + 
			"					incident_sla_tto_passed='yes'  " + 
			"			) C  " + 
			"			; ", nativeQuery=true)
	public List<Object[]> getSummaryByHour(@Param("timeFrom") String timeFrom, @Param("timeTo") String timeTo);
	
}
