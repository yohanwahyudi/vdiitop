package org.vdi.repository.dao;

import java.util.Collection;

import org.vdi.model.ServiceDeskVDI;

public interface BaseDAO {
	
	public void add(ServiceDeskVDI obj);
	public void addAll(Collection<ServiceDeskVDI> col);
	public void deleteEntity();

}
