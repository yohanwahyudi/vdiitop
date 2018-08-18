package org.vdi.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vdi.core.component.itop.ItopHttpConnection;
import org.vdi.core.config.ApplicationProperties;
import org.vdi.model.ServiceDeskVDI;
import org.vdi.services.itopDataLoaderService;

@Service("serviceDeskDataLoaderService")
public class ServiceDeskDataLoaderImpl implements itopDataLoaderService {

	private static final Logger logger = LogManager.getLogger(ServiceDeskDataLoaderImpl.class);

	private final String HTML_REGEX_CLEAR_TAG = "<[^<>]+>";
	private final String HTML_ENTITY_CLEAR = "(&nbsp;|&lt;|&gt;|&amp;|&quot;|&apos;)+";
	private final String UNACCENT_CLEAR = "[^\\p{Print}]";

	private List<ServiceDeskVDI> serviceDeskList;

	@Autowired
	private ApplicationProperties applicatioProperties;

	@Autowired
	private ItopHttpConnection http;

	public ServiceDeskDataLoaderImpl() {

	}

	public Elements parseTableTr(String data) {
		Elements rowsData;
		Document doc = Jsoup.parse(data);
		Element table = doc.select("table").get(0);
		rowsData = table.select("tr");

		return rowsData;
	}

	@Override
	public List<List<String>> parseTableToListByUrl() {

		String url = applicatioProperties.getItopWebHostUrl() + applicatioProperties.getHttpURLItopSD();

		List<List<String>> records = new ArrayList<List<String>>();

		Elements rows;
		try {
			rows = parseTableTr(http.getQueryPhrasebookPageContent(url));

			if (rows != null && rows.size() > 0) {

				for (int i = 1; i < rows.size(); i++) {
					Element row = rows.get(i);
					Elements cols = row.select("td");

					List<String> record = new ArrayList<String>();

					for (int j = 0; j < cols.size(); j++) {
						Element col = cols.get(j);
						List<?> temp = new ArrayList<Object>();

						int size = col.childNodesCopy().size();

						if (size > 1) {
							StringBuffer sb = new StringBuffer();
							temp = col.childNodesCopy();

							Iterator<?> iter = temp.iterator();
							while (iter.hasNext()) {
								sb.append(iter.next());
							}

							record.add(sb.toString());
						} else if (size < 1) {
							record.add("");

						} else {
							temp = col.childNodes();
							Iterator<?> iter = temp.iterator();
							while (iter.hasNext()) {
								Object value = iter.next();

								if (value instanceof TextNode) {
									record.add(value.toString());
								} else if (value instanceof Element) {
									Element element = (Element) value;

									if (element != null) {
										int size1 = element.childNodesCopy().size();
										List<?> temp1 = new ArrayList<Object>();

										if (size1 < 1) {
											record.add("");
										} else {
											StringBuffer sb1 = new StringBuffer();
											temp1 = new ArrayList<Object>();
											temp1 = element.childNodesCopy();

											Iterator<?> iter1 = temp1.iterator();
											while (iter1.hasNext()) {
												sb1.append(iter1.next().toString());
											}

											record.add(sb1.toString());

										}

									}

								}
							}
						}

					}
					records.add(record);

				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;

	}

	@Override
	public List<List<String>> parseTableToListByFile() {

		return null;
	}

	public ServiceDeskVDI mapRowToObject(List<String> row) {
		// regex to replace inside brackets \((.*?)\)
		ServiceDeskVDI sd = new ServiceDeskVDI();

		sd.setScalar_date(row.get(0)); 
		sd.setScalar_time(row.get(1)); 
		sd.setScalar_user(row.get(2)); 
		sd.setScalar_object_class(row.get(3)); 
		sd.setScalar_object_id(row.get(4)); 
		sd.setScalar_attribute(row.get(5)); 
		sd.setScalar_previous_value(row.get(6)); 
		sd.setScalar_new_value(row.get(7)); 
		sd.setScalar_CMDBChangeOp_sub_class(row.get(8)); 
		sd.setIncident_ref(row.get(9));
		sd.setIncident_title(row.get(10)); 
		sd.setIncident_start_date(row.get(11)); 
		sd.setIncident_start_time(row.get(12)); 
		sd.setIncident_end_date(row.get(13)); 
		sd.setIncident_end_time(row.get(14)); 
		sd.setIncident_assignment_date(row.get(15)); 
		sd.setIncident_assignment_time(row.get(16)); 
		sd.setIncident_last_update(row.get(17)); 
		sd.setIncident_last_update_time(row.get(18)); 
		sd.setIncident_close_date(row.get(19)); 
		sd.setIncident_close_time(row.get(20)); 
		sd.setIncident_caller(row.get(21));
		sd.setIncident_agent_Name(row.get(22)); 
		sd.setIncident_agent(row.get(23)); 
		sd.setIncident_team_Name(row.get(24)); 
		sd.setIncident_team(row.get(25)); 
		
//		String description = row.get(26);
//		description = description.replaceAll(HTML_REGEX_CLEAR_TAG, "");
//		description = description.replaceAll(HTML_ENTITY_CLEAR, " ");
//		description = description.replaceAll(UNACCENT_CLEAR, "");
//		if (description.length() > 4000) {
//			description = description.substring(0, 4000);
//		}
//		sd.setIncident_description(description); 
		
		sd.setIncident_status(row.get(26)); 
		sd.setIncident_priority(row.get(27));
		sd.setIncident_origin(row.get(28)); 
		sd.setIncident_last_pending_date(row.get(29)); 
		sd.setIncident_last_pending_time(row.get(30)); 
		sd.setIncident_cumulated_pending(row.get(31)); 
		
		String pendingReason = row.get(32);
		pendingReason = pendingReason.replaceAll(HTML_REGEX_CLEAR_TAG, "");
		pendingReason = pendingReason.replaceAll(HTML_ENTITY_CLEAR, " ");
		pendingReason = pendingReason.replaceAll(UNACCENT_CLEAR, "");
		if (pendingReason.length() > 4000) {
			pendingReason = pendingReason.substring(0, 4000);
		}
		sd.setIncident_pending_reason(pendingReason);
		
		sd.setIncident_parent_incident_ref(row.get(33)); 
		sd.setIncident_ref2(row.get(34)); 
		sd.setIncident_parent_change_ref(row.get(35));
		sd.setIncident_organization(row.get(36)); 
		sd.setIncident_organization_name(row.get(37)); 
		sd.setIncident_sla_tto_passed(row.get(38)); 
		sd.setIncident_sla_tto_over(row.get(39)); 
		sd.setIncident_tto_Deadline(row.get(40));
		sd.setIncident_sla_ttr_passed(row.get(41)); 
		sd.setIncident_sla_ttr_over(row.get(42));
		sd.setIncident_ttr_Deadline(row.get(43)); 
		sd.setIncident_resolution_delay(row.get(44)); 
		
		String solution = row.get(45);
		solution = solution.replaceAll(HTML_REGEX_CLEAR_TAG, "");
		solution = solution.replaceAll(HTML_ENTITY_CLEAR, " ");
		solution = solution.replaceAll(UNACCENT_CLEAR, "");
		if (solution.length() > 4000) {
			solution = solution.substring(0, 4000);
		}
		sd.setIncident_solution(solution); 
		
		sd.setIncident_tto(row.get(46)); 
		sd.setIncident_ttr(row.get(47)); 
		sd.setPerson_full_name(row.get(48));
		sd.setPerson_organization_name(row.get(49));
		sd.setPerson_organization(row.get(50));
		sd.setIncident_user_satisfaction(row.get(51));
		
		String comment = row.get(52);
		comment = comment.replaceAll(HTML_REGEX_CLEAR_TAG, "");
		comment = comment.replaceAll(HTML_ENTITY_CLEAR, " ");
		comment = comment.replaceAll(UNACCENT_CLEAR, "");
		if (comment.length() > 4000) {
			comment = comment.substring(0, 4000);
		}
		sd.setIncident_user_comment(comment);
		
		sd.setIncident_service_name(row.get(53));
		sd.setOrganization_parent_name(row.get(54));

		return sd;
	}
	
	public List<ServiceDeskVDI> mapRowToListObject(List<?> input) {
		List<ServiceDeskVDI> listOfObject = new ArrayList<ServiceDeskVDI>();
		
		for (Iterator<ArrayList> iterator = (Iterator<ArrayList>) input.iterator(); iterator.hasNext();) {
			List<String> row = iterator.next();

			ServiceDeskVDI sd = new ServiceDeskVDI();
			sd = mapRowToObject(row);
			listOfObject.add(sd);
		}

		return listOfObject;
	}

	@Override
	public List<ServiceDeskVDI> getAllDataByURL() {

		List<List<String>> input = parseTableToListByUrl();
		List<ServiceDeskVDI> list = new ArrayList<ServiceDeskVDI>();
		list = mapRowToListObject(input);
		
		return list;
	}

	@Override
	public List<Object> getAllDataByFile() {

		return null;
	}

}
