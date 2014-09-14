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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private DataManager dc;

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView welcome(ModelMap model) throws InterruptedException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");

		List<Alert> storicoList = dc.getAlertList();
		List<Alert> alertList = dc.getAlertList(5);

		// Gson gson = new Gson();
		// String storicoJson = gson.toJson(storicoList);

		// Alert alert = new Alert();
		// alert.setDescription("desc");
		// alert.setSeverity(Severity.HIGH);
		// alert.setTimestamp(new Date());
		// alert.setTitle("title");
		// storicoList.add(alert);
		// mav.addObject("storico", storicoList);

		// String alertJson = gson.toJson(alertList);
		mav.addObject("alerts", alertList);

		return mav;
	}

	@RequestMapping(value = "/alertList", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Alert> getAlertList() {
		List<Alert> alertList = dc.getAlertList();
		return alertList;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public void insert(@RequestBody List<DataRaw> raw) {
		System.out.println("ok");
		dc.insert(raw);
		// return dc.getEvents();
	}
	
	@RequestMapping(value = "/alarms", method = RequestMethod.GET)
	public ModelAndView alarmsView(ModelMap model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alarms");

//		List<Alert> storicoList = dc.getAlertList();
//		List<Alert> alertList = dc.getAlertList(5);
//
//		Gson gson = new Gson();
//
//		String storicoJson = gson.toJson(storicoList);
//		mav.addObject("storico", storicoJson);
//
//		//String alertJson = gson.toJson(alertList);
//		mav.addObject("alerts", alertList);

		return mav;
	}
}
