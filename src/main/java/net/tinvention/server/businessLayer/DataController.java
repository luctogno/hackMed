package net.tinvention.server.businessLayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.tinvention.server.dataLayer.DataManager;
import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.EventType;

public class DataController {

	@Autowired
	private DataManager dm;
	
	public List<DataRaw> getLastTypeValues(EventType type, int numberOfResults){
		return this.dm.GetDataRaw();
	}
	
	
}
