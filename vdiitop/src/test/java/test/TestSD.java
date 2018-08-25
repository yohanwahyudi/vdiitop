package test;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.vdi.core.config.ApplicationProperties;
import org.vdi.core.config.ConfigProperties;
import org.vdi.model.ServiceDeskVDI;
import org.vdi.repository.dao.ServiceDeskDAO;
import org.vdi.services.itopDataLoaderService;

public class TestSD {

	private static final Logger logger = LogManager.getLogger(TestSD.class);
	
	public static void main(String args[]) throws IllegalArgumentException, IllegalAccessException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigProperties.class);
		
		itopDataLoaderService itopService = ctx.getBean("serviceDeskDataLoaderService", itopDataLoaderService.class);
		List<ServiceDeskVDI> listSD = (List<ServiceDeskVDI>) itopService.getAllDataByURL();
//		
//		logger.debug(listSD.size());
//		
//		ServiceDeskVDI sd = listSD.get(0); 
//		for(Field field : sd.getClass().getDeclaredFields()) {
//			field.setAccessible(true);
//			String name = field.getName();
//			Object val = field.get(sd);
//			
//			logger.debug(name+" : " +val);
//		}
		
		ServiceDeskDAO sdDAO = ctx.getBean("serviceDeskDAO", ServiceDeskDAO.class);
		sdDAO.addAll(listSD);
		
		List<Object[]> objList = sdDAO.getSummaryByTime("00:01:00","08:00:00");
		
		for(Object[] obj:objList) {
			for(int i=0; i<obj.length; i++) {
				logger.debug(obj[i]);
			}
		}
		
		ctx.close();
		
	}
	
}
