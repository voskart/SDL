package controller;

import model.User;

import org.basex.core.BaseXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.springframework.web.context.support.ServletContextResource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import service.DatabaseService;

import service.DatabaseService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

import java.util.UUID;
/**
 * Created by voskart on 11.06.15.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
    private DatabaseService dbservice;
	
    @Autowired
    private DatabaseService dbservice;

    @Autowired
    private ServletContext servletContext;
    private static final Logger logger = Logger.getLogger(String.valueOf(RegistrationController.class));

    @RequestMapping(method = RequestMethod.POST)
    public String printWelcome(HttpServletRequest req, ModelMap model) {
        logger.info("Successfully submitted registration form");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // We now generate a unique identifier for the user
        UUID uuid = UUID.randomUUID();
        // Create the user-object if needed
        User user = new User(username, password, uuid.toString());
        // Save user to XML-file
        saveUser(user);
        // Redirect user to needed page, you can also pass the user-object once again
        model.addAttribute(model.addAttribute("username", username));
        return "HotOrNot";
    }

    private void saveUser(User _user){
        try {
            dbservice.insertNewUserData(_user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void saveUser(User user){
    	try {
			this.dbservice.insertNewUserData(user);
		} catch (BaseXException e) {
			RegistrationController.logger.info(e.getMessage());
		}
    }

//    private void saveUserToXML(User _user){
//
//        String tmp_enc_password = "";
//        ServletContextResource servletContextResource = null;
//        File userXML = null;
//
//        try {
//
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//
//            InputStream inputStream = null;
//            try {
//                ServletContextResource resource = new ServletContextResource(servletContext,"/WEB-INF/content/users.xml");
//                userXML = resource.getFile();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            Document document = documentBuilder.parse(userXML);
//            Element root = document.getDocumentElement();
//
//            Collection<User> users = new ArrayList<User>();
//            users.add(_user);
//
//            for (User user : users) {
//                // Users XML consists of users
//                Element newUser = document.createElement("user");
//                root.appendChild(newUser);
//                // Create new node for the username and append it
//                Element userName = document.createElement("username");
//                userName.appendChild(document.createTextNode(user.getUsername()));
//                newUser.appendChild(userName);
//                // Create new node for the hash of the password
//                Element password = document.createElement("password");
//                tmp_enc_password = LoginController.encryptPassword(user.getPassword());
//                password.appendChild(document.createTextNode(tmp_enc_password));
//                newUser.appendChild(password);
//                // Create new node for the uuid of the registered user
//                Element uuid = document.createElement("uuid");
//                uuid.appendChild(document.createTextNode(user.getID().toString()));
//                newUser.appendChild(uuid);
//
//                root.appendChild(newUser);
//            }
//
//            DOMSource source = new DOMSource(document);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            StreamResult result = new StreamResult(userXML);
//            transformer.transform(source, result);
//
//        }catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // Method for get-requests, to display the actual form
    @RequestMapping(method = RequestMethod.GET)
    public String showRegistration(ModelMap model) {
        return "registration";
    }
}
