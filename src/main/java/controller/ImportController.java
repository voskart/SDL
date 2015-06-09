package controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.ImportService;



@Controller
@RequestMapping("/import")
public class ImportController {
	@Autowired
	ImportService importservice;

	private static Logger log = Logger.getLogger(ImportController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) throws MalformedURLException, IOException {
		importservice.importtoDB();
		return "hello";
	}
}