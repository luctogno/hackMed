package net.tinvention.server.controller;

import java.util.List;

import net.tinvention.server.businessLayer.DataManager;
import net.tinvention.server.model.Alert;
import net.tinvention.server.model.DataRaw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private DataManager dm;

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String welcome(ModelMap model) throws InterruptedException {
		List<Alert> toReturn = dm.getAlertList();
		model.put("alarms", toReturn);
		return "index";
	}

	@RequestMapping(value = "/alertList", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Alert> getAlertList() {
		return dm.getAlertList();
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public void insert(@RequestBody List<DataRaw> raw) {
		System.out.println(raw.size());
		dm.insert(raw);
		// return dc.getEvents();
	}
}


