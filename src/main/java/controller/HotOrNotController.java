package controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/HotOrNot")
public class HotOrNotController {
	

	private static Logger log = Logger.getLogger(HotOrNotController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		String stone = ""; //hier muss der Link zu dem Bild des Steines hin
		model.addAttribute("image", stone);
		return "HotOrNot";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String like( HttpServletRequest req,ModelMap model) {
		String stone = ""; //hier muss der Link zu dem Bild des Steines hin
		model.addAttribute("image", stone);
		String value = req.getParameter("x"); // Value = hot wenn gemocht. Value = not wenn nicht emocht
		return "HotOrNot";
	}
}