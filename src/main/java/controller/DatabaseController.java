package controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.DatabaseService;


@Controller
@RequestMapping("/db")
public class DatabaseController {
	@Autowired
	private DatabaseService dbService;
    private static final Logger log = Logger.getLogger(String.valueOf(DatabaseController.class));
	
    @PostConstruct
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String startDB() throws Exception{
		log.info("Start DB");
		dbService.openBasexDatabase();
		return "hello";
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public String stopDB() throws Exception{
		log.info("Stop DB");
		dbService.closeBasexDatabase();
        return ("hello");
	}
	
	
	//Z.B: http://localhost:8080/SDL/db/export?path="C:/database"
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String exportDB(HttpServletRequest request) throws Exception{
		log.info("Export DB to"+request.getParameter("path"));
		dbService.exportData(request.getParameter("path"));
        return ("hello");
	}
	
}
