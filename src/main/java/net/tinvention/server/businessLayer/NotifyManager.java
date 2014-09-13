package net.tinvention.server.businessLayer;

import java.util.List;

import net.tinvention.server.model.Alert;

import org.springframework.stereotype.Service;

@Service
public class NotifyManager {

	public List<Alert> filterSevereAlerts(List<Alert> alerts){
		return alerts; //TODO
	}

	public void notifySevereAlerts(List<Alert> alerts){
		//TODO
	}
}
