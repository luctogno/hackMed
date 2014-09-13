package net.tinvention.TinSleep.Server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class IndexController {

		//@Autowired
		//private ArduinoMexManager arduinoMexManager;
			
		@RequestMapping(value="/index.html", method = RequestMethod.GET)
		public String welcome(ModelMap model) throws InterruptedException {
			return "index";
		}
}
