package org.vdi.services;

import java.util.List;

public interface itopDataLoaderService {

	public List<List<String>> parseTableToListByUrl();
	public List<List<String>> parseTableToListByFile();
	
	public Object mapRowToObject(List<String> row);
	public List<?> mapRowToListObject(List<?> list);
	
	public List<?> getAllDataByURL(); 
	public List<?> getAllDataByFile();
	
}
