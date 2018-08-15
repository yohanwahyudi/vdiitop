package test.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.vdi.component.itop.ItopHttpConnection;
import org.vdi.config.ApplicationProperties;

public class TestHttpClient {
	
	private final static Logger logger = LogManager.getLogger(TestHttpClient.class);
	
	public static void main (String args[]) throws ClientProtocolException, IOException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationProperties.class);
//		AnnotationConfigApplicationContext ctx1 = new AnnotationConfigApplicationContext(ConfigProperties.class);
//		HttpClientRequest props = ctx.getBean("httpClientRequest",HttpClientRequest.class);
//		
//		logger.debug(props.readUrl("http://139.255.57.82/itop/pages/UI.php"));
		
		ItopHttpConnection itopHttp = ctx.getBean("itopHttpConnection",ItopHttpConnection.class);
		ApplicationProperties props = ctx.getBean(ApplicationProperties.class);
		
		String url = props.getItopWebHostUrl()+props.getHttpURLItopSD();
		
		String result = 
				itopHttp.getQueryPhrasebookPageContent(
						url);
		logger.debug(result);
		ctx.close();
	}

}
