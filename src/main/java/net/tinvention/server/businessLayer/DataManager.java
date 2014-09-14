package net.tinvention.server.businessLayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.tinvention.server.dataLayer.DataRawDao;
import net.tinvention.server.model.Alert;
import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.Event;
import net.tinvention.server.model.EventType;
import net.tinvention.server.utils.PeakDetector;

import org.apache.commons.lang.ArrayUtils;
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

	public List<List<Event>> getEventList(){
		List<List<Event>> resultList = new ArrayList<>();
		
		List<Event> ACC = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.ACC, 50)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.ACC);
			ev.setTimestamp(raw.getTimestamp());
			ev.setAccX(raw.getAccX());
			ev.setAccY(raw.getAccY());
			ev.setAccZ(raw.getAccZ());
		}
		resultList.add(ACC);
		
		List<Event> TEMP = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.TEMP, 50)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.TEMP);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		resultList.add(TEMP);
		
		List<Event> BVP = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.BVP, 50)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.BVP);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		resultList.add(BVP);
		
		List<Event> GSR = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.GSR, 50)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.GSR);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		resultList.add(GSR);
		
		List<Event> IBI = new ArrayList<>();
		for(DataRaw raw : dm.GetDataRaw(EventType.IBI, 50)){ //Ultimi 50 elementi per tipo
			Event ev = new Event();
			ev.setType(EventType.IBI);
			ev.setTimestamp(raw.getTimestamp());
			ev.setValue(raw.getValue());
		}
		resultList.add(IBI);
		
		return resultList;
	}

	public List<List<Event>> getEventsToBeAggregated(){
		return getEventList();
	}

	List<DataRaw> getLastTypeValues(EventType type, int numberOfResults){
		return dm.GetDataRaw();
	}

	public List<Alert> aggregateEvents(List<List<Event>> eventToBeAggregated){
		List<Alert> alertList = new ArrayList<>();
		
		//APNEE
		List<Float> misure = new ArrayList<>();
		for(Event e: eventToBeAggregated.get(2)){
			misure.add(e.getValue());
		}

		PeakDetector peakDetector = new PeakDetector((Float[])misure.toArray());
		peakDetector.process(64*3, (float)0.10);
		List<Integer> peaks = peakDetector.getPeaksPos();
		Collections.sort(peaks);
		
		for(int i =1; i<peaks.size(); i++){
			if(peaks.get(i-1) - peaks.get(i) > 15*64){
				
				Alert alert = new Alert();
				alert.setTimestamp(new Date());
				alert.setTitle("Apnea");
				alert.setDescription("ATTENZIONE: Sei entrato in apnea");
				
				alertList.add(alert);
			}
		}
		
		//TODO: save

		return alertList;
	}

}
