package net.tinvention.server.controller;

import java.util.List;

import net.tinvention.server.businessLayer.DataController;
import net.tinvention.server.model.DataRaw;
import net.tinvention.server.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("/")
public class IndexController {

		//@Autowired
		//private ArduinoMexManager arduinoMexManager;
	
		@Autowired
		private DataController dc;
			
		@RequestMapping(value="/index.html", method = RequestMethod.GET)
		public String welcome(ModelMap model) throws InterruptedException {
			return "index";
		}
		
		
		@RequestMapping(value = "/eventList", method = RequestMethod.GET, produces = { "application/json" })
		@ResponseStatus(HttpStatus.OK)
		public List<List<Event>> getEventsList() {
			return dc.getEvents();
		}
		
		@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = { "application/json" })
		@ResponseStatus(HttpStatus.OK)
		public void insert(@RequestBody List<DataRaw> raw) {
//			return dc.getEvents();
		}
	}
