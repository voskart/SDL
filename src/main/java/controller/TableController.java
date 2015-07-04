package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.XSLTService;



@Controller
@RequestMapping("/Table")
public class TableController {
	
	private static Logger log = Logger.getLogger(TableController.class);

	@Autowired
	XSLTService xService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("table", xService.getStoneTable());
		return "Table";
	}
}