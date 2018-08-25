package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.vdi.core.config.ConfigProperties;
import org.vdi.core.constant.ConstantProperties;
import org.vdi.helper.populate.PopulateServiceDesk;

public class Test2SD {

	private static final Logger logger = LogManager.getLogger(Test2SD.class);
	
	public static void main (String args[]) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigProperties.class);
		
		PopulateServiceDesk populateSD = ctx.getBean("populateServiceDesk", PopulateServiceDesk.class);
		
		logger.debug(populateSD.getMonthlySummaryPerformance());
		logger.debug(populateSD.getWeeklySummaryPerformance());
		logger.debug(populateSD.getShiftlySummaryPerformance("08:00:00", "16:00:00", ConstantProperties.CONSTANT_PERIOD_SHIFT_1));
		
		ctx.close();
	}
	
}
