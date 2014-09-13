package net.tinvention.server.businessLayer;

import java.util.ArrayList;
import java.util.List;

import net.tinvention.server.dataLayer.DataRawDao;
import net.tinvention.server.model.Alert;
import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.Event;
import net.tinvention.server.model.EventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataManager {

	@Autowired
	private DataRawDao dm;

	public List<Alert> getAlertList(){
		// TODO lista completa degli alert
		return new ArrayList<Alert>();
	}

	public List<Event> getEventList(){
		// TODO lista completa degli eventi
		return new ArrayList<Event>();
	}

	public List<Event> getEventsToBeAggregated(){
		// TODO lista degli eventi da aggregare x fare gli alert
		return new ArrayList<Event>();
	}

	List<DataRaw> getLastTypeValues(EventType type, int numberOfResults){
		return dm.GetDataRaw();
	}

	public List<Alert> aggregateEvents(List<Event> eventToBeAggregated){
		List<Alert> alertList = new ArrayList<>();

		Alert alert = new Alert(); 
		
		alertList.add(alert); // TODO analizzare i dati
		
		//		List<Event> ACC = new ArrayList<>();
		//		for(DataRaw raw : dm.GetDataRaw(EventType.ACC, 50)){ //Ultimi 50 elementi per tipo
		//			Event ev = new Event();
		//			ev.setType(EventType.ACC);
		//			ev.setTimestamp(raw.getTimestamp());
		//			ev.setAccX(raw.getAccX());
		//			ev.setAccY(raw.getAccY());
		//			ev.setAccZ(raw.getAccZ());
		//		}
		//		alertList.add(ACC);
		//		
		//		List<Event> TEMP = new ArrayList<>();
		//		for(DataRaw raw : dm.GetDataRaw(EventType.TEMP, 50)){ //Ultimi 50 elementi per tipo
		//			Event ev = new Event();
		//			ev.setType(EventType.TEMP);
		//			ev.setTimestamp(raw.getTimestamp());
		//			ev.setValue(raw.getValue());
		//		}
		//		alertList.add(TEMP);
		//		
		//		List<Event> BVP = new ArrayList<>();
		//		for(DataRaw raw : dm.GetDataRaw(EventType.BVP, 50)){ //Ultimi 50 elementi per tipo
		//			Event ev = new Event();
		//			ev.setType(EventType.BVP);
		//			ev.setTimestamp(raw.getTimestamp());
		//			ev.setValue(raw.getValue());
		//		}
		//		alertList.add(BVP);
		//		
		//		List<Event> GSR = new ArrayList<>();
		//		for(DataRaw raw : dm.GetDataRaw(EventType.GSR, 50)){ //Ultimi 50 elementi per tipo
		//			Event ev = new Event();
		//			ev.setType(EventType.GSR);
		//			ev.setTimestamp(raw.getTimestamp());
		//			ev.setValue(raw.getValue());
		//		}
		//		alertList.add(GSR);
		//		
		//		List<Event> IBI = new ArrayList<>();
		//		for(DataRaw raw : dm.GetDataRaw(EventType.IBI, 50)){ //Ultimi 50 elementi per tipo
		//			Event ev = new Event();
		//			ev.setType(EventType.IBI);
		//			ev.setTimestamp(raw.getTimestamp());
		//			ev.setValue(raw.getValue());
		//		}
		//		alertList.add(IBI);

		return alertList;
	}

}
