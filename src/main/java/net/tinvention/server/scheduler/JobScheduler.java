package net.tinvention.server.scheduler;

import java.util.List;

import net.tinvention.server.businessLayer.DataManager;
import net.tinvention.server.businessLayer.NotifyManager;
import net.tinvention.server.model.Alert;
import net.tinvention.server.model.DataRaw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class JobScheduler {

	@Autowired
	DataManager dm;
	@Autowired
	NotifyManager nm;

	@Scheduled(fixedRate = 5000)
	// millisecondi
	public void getEventsAndStartAnalysys() {
		List<DataRaw> events = dm.getEventsToBeAggregated();
		List<Alert> alerts = dm.aggregateEvents(events);
		List<Alert> severe = nm.filterSevereAlerts(alerts);
		nm.notifySevereAlerts(severe);
	}
}
