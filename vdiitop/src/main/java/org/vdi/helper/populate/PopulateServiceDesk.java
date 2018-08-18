package org.vdi.helper.populate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.vdi.core.component.time.TimeStatic;
import org.vdi.core.constant.ConstantProperties;
import org.vdi.model.SummaryPerformance;
import org.vdi.repository.dao.PerformanceSummaryDAO;
import org.vdi.repository.dao.ServiceDeskDAO;

public class PopulateServiceDesk {
	
	private final static Logger logger = LogManager.getLogger(PopulateServiceDesk.class);
	
	@Autowired
	@Qualifier("serviceDeskDAO")
	ServiceDeskDAO sdDAO;
	
	@Autowired
	@Qualifier("performanceSummaryDAO")
	PerformanceSummaryDAO summaryDAO;
	
	private int currentYear;
	private int currentMonth;
	private int currentWeekYear;
	private int previousMonth;
	private int previousWeekYear;
	
	public PopulateServiceDesk() {
		this.currentYear = TimeStatic.currentYear;
		
		this.currentMonth = TimeStatic.currentMonth;
		this.currentWeekYear = TimeStatic.currentWeekMonth;
		
		this.previousMonth = currentMonth-1;
		this.previousWeekYear = currentWeekYear-1;
	}
	
	//ServiceDesk 
	//monthly
	public SummaryPerformance getMonthlySummaryPerformance(int month) {
		String period = ConstantProperties.CONSTANT_PERIOD_MONTHLY;
		String category = ConstantProperties.CONSTANT_PERIOD_WEEKLY;
	}
	
	public SummaryPerformance getWeeklySummaryPerformance(int week) {
		String period = ConstantProperties.CONSTANT_PERIOD_MONTHLY;
		String category = ConstantProperties.CONSTANT_PERIOD_WEEKLY;
	}
	
}
