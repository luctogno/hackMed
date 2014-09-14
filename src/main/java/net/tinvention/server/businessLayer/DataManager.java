package net.tinvention.server.businessLayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.tinvention.server.dataLayer.AlertDao;
import net.tinvention.server.dataLayer.DataRawDao;
import net.tinvention.server.model.Alert;
import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.EventType;
import net.tinvention.server.utils.PeakDetector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class DataManager {

	@Autowired
	private DataRawDao dm;

	@Autowired
	private AlertDao dmAlert;

	public List<Alert> getAlertList() {
		return dmAlert.GetDataRaw();
	}

	public List<Alert> getAlertList(int limit) {
		return dmAlert.GetDataRaw(limit);
	}

	public List<List<DataRaw>> getEventList() {
		List<List<DataRaw>> resultList = new ArrayList<>();

		List<DataRaw> ACC = new ArrayList<>();
		List<DataRaw> accDB = dm.GetDataRaw(EventType.ACC, 50);

		if (!CollectionUtils.isEmpty(accDB)) {
			for (DataRaw raw : accDB) { // Ultimi 50 elementi per tipo
				DataRaw ev = new DataRaw();
				ev.setType(EventType.ACC);
				ev.setTimestamp(raw.getTimestamp());
				ev.setAccX(raw.getAccX());
				ev.setAccY(raw.getAccY());
				ev.setAccZ(raw.getAccZ());
				ACC.add(ev);
			}
		}
		resultList.add(ACC);

		List<DataRaw> TEMP = new ArrayList<>();
		List<DataRaw> tempDB = dm.GetDataRaw(EventType.TEMP, 50);

		if (!CollectionUtils.isEmpty(tempDB)) {
			for (DataRaw raw : tempDB) { // Ultimi 50
				// elementi per
				// tipo
				DataRaw ev = new DataRaw();
				ev.setType(EventType.TEMP);
				ev.setTimestamp(raw.getTimestamp());
				ev.setValue(raw.getValue());
				TEMP.add(ev);
			}
		}
		resultList.add(TEMP);

		List<DataRaw> BVP = new ArrayList<>();
		List<DataRaw> bvpDB = dm.GetDataRaw(EventType.BVP, 50);

		if (!CollectionUtils.isEmpty(bvpDB)) {
			for (DataRaw raw : bvpDB) { // Ultimi 50
				// elementi per
				// tipo
				DataRaw ev = new DataRaw();
				ev.setType(EventType.BVP);
				ev.setTimestamp(raw.getTimestamp());
				ev.setValue(raw.getValue());
				BVP.add(ev);
			}
		}
		resultList.add(BVP);

		List<DataRaw> GSR = new ArrayList<>();
		List<DataRaw> gsrDB = dm.GetDataRaw(EventType.GSR, 50);

		if (!CollectionUtils.isEmpty(gsrDB)) {
			for (DataRaw raw : gsrDB) { // Ultimi 50
				// elementi per
				// tipo
				DataRaw ev = new DataRaw();
				ev.setType(EventType.GSR);
				ev.setTimestamp(raw.getTimestamp());
				ev.setValue(raw.getValue());
				GSR.add(ev);
			}
		}
		resultList.add(GSR);

		List<DataRaw> IBI = new ArrayList<>();
		List<DataRaw> ibiDB = dm.GetDataRaw(EventType.IBI, 50);

		if (!CollectionUtils.isEmpty(ibiDB)) {
			for (DataRaw raw : ibiDB) { // Ultimi 50
				// elementi per
				// tipo
				DataRaw ev = new DataRaw();
				ev.setType(EventType.IBI);
				ev.setTimestamp(raw.getTimestamp());
				ev.setValue(raw.getValue());
				IBI.add(ev);
			}
		}
		resultList.add(IBI);

		return resultList;
	}

	public List<List<DataRaw>> getEventsToBeAggregated() {
		return getEventList();
	}

	List<DataRaw> getLastTypeValues(EventType type, int numberOfResults) {
		return dm.GetDataRaw();
	}

	public List<Alert> aggregateEvents(List<List<DataRaw>> eventToBeAggregated) {
		List<Alert> alertList = new ArrayList<>();

		// APNEE
		List<Float> misure = new ArrayList<>();
		for (DataRaw e : eventToBeAggregated.get(2)) {
			misure.add(e.getValue());
		}

		PeakDetector peakDetector = new PeakDetector(
				misure.toArray(new Float[0]));
		peakDetector.process(64 * 3, (float) 0.10);
		List<Integer> peaks = peakDetector.getPeaksPos();
		if (peaks != null) {
			Collections.sort(peaks);
			for (int i = 1; i < peaks.size(); i++) {
				if ((peaks.get(i - 1) - peaks.get(i)) > (15 * 64)) {

					Alert alert = new Alert();
					alert.setTimestamp(new Date());
					alert.setTitle("Apnea");
					alert.setDescription("ATTENZIONE: Sei entrato in apnea");

					alertList.add(alert);
				}
			}
		}

		dmAlert.InsertData(alertList);

		return alertList;
	}

	public void insert(List<DataRaw> data) {
		dm.InsertData(data);
	}

}
