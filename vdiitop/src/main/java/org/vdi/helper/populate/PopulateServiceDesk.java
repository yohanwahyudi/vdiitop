package org.vdi.helper.populate;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.vdi.core.component.time.TimeStatic;
import org.vdi.core.constant.ConstantProperties;
import org.vdi.model.SummaryPerformance;
import org.vdi.repository.dao.PerformanceSummaryDAO;
import org.vdi.repository.dao.ServiceDeskDAO;

@Component("populateServiceDesk")
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

	private final String weeklyPeriod = ConstantProperties.CONSTANT_PERIOD_WEEKLY;
	private final String monthlyPeriod = ConstantProperties.CONSTANT_PERIOD_MONTHLY;
	private final String shiftPeriod = ConstantProperties.CONSTANT_PERIOD_SHIFT;
	private final String sdCategory = ConstantProperties.CONSTANT_CATEGORY_SD;

	public PopulateServiceDesk() {
		this.currentYear = TimeStatic.currentYear;
		this.currentMonth = TimeStatic.currentMonth;
		this.currentWeekYear = TimeStatic.currentWeekYear;
//		this.previousMonth = currentMonth - 1;
		this.previousMonth = currentMonth;
		this.previousWeekYear = currentWeekYear - 1;

	}

	// ServiceDesk
	// weekly
	@SuppressWarnings("unchecked")
	public SummaryPerformance getWeeklySummaryPerformance() {

		List<Object[]> newSummaryObjectList = new ArrayList<Object[]>();
		newSummaryObjectList = (List<Object[]>) sdDAO.getSummaryByWeek(previousWeekYear);
		
		SummaryPerformance newSummaryPerformance = getSummaryPerformance(newSummaryObjectList, weeklyPeriod, sdCategory);

		logger.debug("inside getWeeklySummaryPerformance...");
		logger.debug("objectList: " + newSummaryObjectList.size());

		List<Object[]> existingObjectList = new ArrayList<Object[]>();
		existingObjectList = (List<Object[]>) summaryDAO.getPerformanceByWeek(weeklyPeriod, sdCategory,
				currentWeekYear);
		if (existingObjectList != null && existingObjectList.size() > 0) {

			SummaryPerformance existingSummaryPerformance = getSummaryPerformance(existingObjectList, weeklyPeriod, sdCategory);
			existingSummaryPerformance.setTotalTicket(newSummaryPerformance.getTotalTicket());
			existingSummaryPerformance.setTotalAchieved(newSummaryPerformance.getTotalAchieved());
			existingSummaryPerformance.setAchievement(newSummaryPerformance.getAchievement());

			return existingSummaryPerformance;

		} else {

			return newSummaryPerformance;

		}

	}

	@SuppressWarnings("unchecked")
	public SummaryPerformance getMonthlySummaryPerformance() {

		List<Object[]> newSummaryObjectList = new ArrayList<Object[]>();
		newSummaryObjectList = sdDAO.getSummaryByMonth(previousMonth);

		SummaryPerformance newSummaryPerformance = getSummaryPerformance(newSummaryObjectList, monthlyPeriod, sdCategory);

		logger.debug("inside getMonthlySummaryPerformance...");
		logger.debug("newobjectList: " + newSummaryObjectList.size());

		List<Object[]> existingObjectList = new ArrayList<Object[]>();
		existingObjectList = (List<Object[]>) summaryDAO.getPerformanceByMonth(monthlyPeriod, sdCategory, currentMonth);

		if (existingObjectList != null && existingObjectList.size() > 0) {

			SummaryPerformance existingSummaryPerformance = getSummaryPerformance(existingObjectList, monthlyPeriod, sdCategory);
			existingSummaryPerformance.setTotalTicket(newSummaryPerformance.getTotalTicket());
			existingSummaryPerformance.setTotalAchieved(newSummaryPerformance.getTotalAchieved());
			existingSummaryPerformance.setAchievement(newSummaryPerformance.getAchievement());

			return existingSummaryPerformance;

		} else {

			return newSummaryPerformance;

		}

	}
	
	@SuppressWarnings("unchecked")
	public SummaryPerformance getShiftlySummaryPerformance(String timeFrom, String timeTo, String shift) {
		
		//should add time validator here
		//format hh:mm:ss
		
		List<Object[]> newSummaryObjectList = new ArrayList<Object[]>();
		newSummaryObjectList = sdDAO.getSummaryByTime(timeFrom, timeTo);
		
		SummaryPerformance newSummaryPerformance = getSummaryPerformance(newSummaryObjectList, shift, sdCategory);

		logger.debug("inside getShiftlySummaryPerformance...");
		logger.debug("newobjectList: " + newSummaryObjectList.size());
		
		List<Object[]> existingObjectList = new ArrayList<Object[]>();
		existingObjectList = (List<Object[]>) summaryDAO.getPerformanceByShift(shift, sdCategory);

		if (existingObjectList != null && existingObjectList.size() > 0) {

			SummaryPerformance existingSummaryPerformance = getSummaryPerformance(existingObjectList, shiftPeriod, sdCategory);
			existingSummaryPerformance.setTotalTicket(newSummaryPerformance.getTotalTicket());
			existingSummaryPerformance.setTotalAchieved(newSummaryPerformance.getTotalAchieved());
			existingSummaryPerformance.setAchievement(newSummaryPerformance.getAchievement());

			return existingSummaryPerformance;

		} else {

			return newSummaryPerformance;

		}
		
	}

	private BigDecimal getAchievementTicket(BigDecimal ticketAchieved, BigDecimal ticketTotal) {

		BigDecimal achievement = new BigDecimal(0);

		try {
			achievement = ticketAchieved.divide(ticketTotal, 4, BigDecimal.ROUND_HALF_UP);
			achievement = achievement.multiply(new BigDecimal(100));
		} catch (ArithmeticException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return achievement;
	}

	private SummaryPerformance getSummaryPerformance(List<Object[]> objectList, String period, String category) {

		Integer totalTicket = 0;
		Integer achievedTicket = 0;
		Integer missedTicket = 0;
		for (Object[] obj : objectList) {
			
			for(int i=0; i<obj.length; i++) {
			
				logger.debug(obj[i]);
			}
			
			totalTicket = ((BigInteger) obj[0]).intValue();
			achievedTicket = ((BigInteger) obj[1]).intValue();
			missedTicket = ((BigInteger) obj[2]).intValue();

		}
		BigDecimal achievement = getAchievementTicket(new BigDecimal(achievedTicket), new BigDecimal(totalTicket));

		SummaryPerformance newSummary = new SummaryPerformance();
		newSummary.setTotalTicket(totalTicket);
		newSummary.setTotalAchieved(achievedTicket);
		newSummary.setTotalMissed(missedTicket);
		newSummary.setAchievement(achievement.floatValue());
		newSummary.setPeriod(period);
		newSummary.setCategory(category);
		newSummary.setMonth((short) currentMonth);

		return newSummary;

	}

}
