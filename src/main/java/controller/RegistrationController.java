package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.xquery.XQException;

import model.User;

import org.basex.core.BaseXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.DatabaseService;
/**
 * Created by voskart on 11.06.15.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
    private DatabaseService dbservice;

    @Autowired
    private ServletContext servletContext;
    private static final Logger logger = Logger.getLogger(String.valueOf(RegistrationController.class));

    @RequestMapping(method = RequestMethod.POST)
    public String printWelcome(HttpServletRequest req, ModelMap model) throws XQException, IOException {
        logger.info("Successfully submitted registration form");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String passwordHash = LoginController.encryptPassword(password);
        // Create the user-object if needed
        Integer newID = dbservice.getLastUserID();

        User user = new User(username, passwordHash, newID + 1);
        // Save user to XML-file
        saveUser(user);
        // Redirect user to needed page, you can also pass the user-object once again
        model.addAttribute(model.addAttribute("username", username));
        for (String u: dbservice.getAllUsers()){
        	logger.info("User: "+u+"\n");
        }
        
        return "HotOrNot";
    }
    
    private void saveUser(User user) throws XQException, IOException{
    	try {
			this.dbservice.insertNewUserData(user);
		} catch (BaseXException e) {
			RegistrationController.logger.info(e.getMessage());
		}
    }

    // Method for get-requests, to display the actual form
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistration(ModelMap model) {
        return "registration";
    }
}
