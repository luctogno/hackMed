
package net.tinvention.server.scheduler;

import java.util.List;

import net.tinvention.server.businessLayer.DataManager;
import net.tinvention.server.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class JobScheduler {

	@Autowired
	DataManager dm;
	
	@Scheduled(fixedRate=5000) //millisecondi
	public void getEventsAndStartAnalysys() {
		List<List<Event>> events = dm.getEventsToBeAggregated();
		dm.aggregateEvents(events);
	}
}
