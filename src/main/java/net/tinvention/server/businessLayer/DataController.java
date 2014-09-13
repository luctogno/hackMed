package net.tinvention.server.businessLayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tinvention.server.dataLayer.DataManager;
import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.Event;
import net.tinvention.server.model.EventType;

@Service
public class DataController {
	
	private final int EVENT_NUMBER = 50;

	@Autowired
	private DataManager dm;
	
	public List<List<Event>> getEvents(){
		List<List<Event>> eventsList = new ArrayList<>();
		
		List<Event> ACC = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.ACC, EVENT_NUMBER)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.ACC);
			ev.setTimestamp(raw.getTimestamp());
			ev.setAccX(raw.getAccX());
			ev.setAccY(raw.getAccY());
			ev.setAccZ(raw.getAccZ());
		}
		eventsList.add(ACC);
		
		List<Event> TEMP = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.TEMP, EVENT_NUMBER)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.TEMP);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		eventsList.add(TEMP);
		
		List<Event> BVP = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.BVP, EVENT_NUMBER)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.BVP);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		eventsList.add(BVP);
		
		List<Event> GSR = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.GSR, EVENT_NUMBER)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.GSR);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		eventsList.add(GSR);
		
		List<Event> IBI = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.IBI, EVENT_NUMBER)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.IBI);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		eventsList.add(IBI);
		
		return eventsList;
	}
	
	 List<DataRaw> getLastTypeValues(EventType type, int numberOfResults){
		return this.dm.GetDataRaw();
	}
	
	
}
