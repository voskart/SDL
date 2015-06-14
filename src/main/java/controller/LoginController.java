package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Logger;

import java.io.File;

import org.springframework.web.context.support.ServletContextResource;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by voskart on 11.06.15.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ServletContext servletContext;
    private static final Logger logger = Logger.getLogger(String.valueOf(LoginController.class));

    // Fetch the user-information from the model
    @RequestMapping(method = RequestMethod.POST)
    public String login(
            HttpServletRequest req, HttpServletResponse resp,
            ModelMap model) {

        logger.info("Successfully logged in");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Hash the password, for dem safety-reasons
        String passwordHash = encryptPassword(password);
        // This user-object can be used for further actions
        // TODO: Return the user's uuid for further actions?
        User tmp_user = new User(username, passwordHash);

        logger.info("username: " + username + ", password: " + passwordHash);

        boolean tmp_bool = validateUserInXML(tmp_user);

        // Check if the user is in the XML file
        if (tmp_bool){
            model.addAttribute("username", username);
            return "successpage";
        }else{
            // Else return an errorpage
            model.addAttribute("username", username);
            model.addAttribute("password", passwordHash);
            return "errorpage";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public static String showForm(){
        return "login";
    }

    protected static String encryptPassword(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private boolean validateUserInXML(User user) {

        String search_user = user.getUsername();
        String search_pw = user.getPassword();
        Map<String, ArrayList<String>> userList = new HashMap<String, ArrayList<String>>();
        File userXML = null;
        ServletContextResource servletContextResource = null;

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            InputStream inputStream = null;
            try {
                servletContextResource = new ServletContextResource(servletContext,"/WEB-INF/content/users.xml");

                userXML = servletContextResource.getFile();

            } catch (IOException e) {
                e.printStackTrace();
            }


            Document doc = docBuilder.parse(userXML);

            // normalize text representation
            doc.getDocumentElement ().normalize ();


            NodeList listOfUsers = doc.getElementsByTagName("user");
            int totalUsers = listOfUsers.getLength();
            logger.info("Total no of users : " + totalUsers);

            for (int temp = 0; temp < listOfUsers.getLength(); temp++) {

                String tmp_username = "";
                String tmp_password = "";
                String tmp_uuid = "";

                Node nNode = listOfUsers.item(temp);

                logger.info("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    tmp_username = eElement.getElementsByTagName("username").item(0).getTextContent();
                    tmp_password = eElement.getElementsByTagName("password").item(0).getTextContent();
                    tmp_uuid = eElement.getElementsByTagName("uuid").item(0).getTextContent();

                    // Create a temporary list for the data (password-hash and uuid)
                    ArrayList<String> tmp_list = new ArrayList<String>();
                    tmp_list.add(tmp_password);
                    tmp_list.add(tmp_uuid);
                    // Add the username (key) together with the data (value) to the HashMap
                    userList.put(tmp_username, tmp_list);
                    // HERE (Sinur & Kevin): Possible creation of the user-object
                    // User user = new User(un, pw, uuid) & further work, database, new stone etc.
                    // Logger
                    logger.info("Username : " + eElement.getElementsByTagName("username").item(0).getTextContent());
                    logger.info("Password : " + eElement.getElementsByTagName("password").item(0).getTextContent());
                    logger.info("UUID : " + eElement.getElementsByTagName("uuid").item(0).getTextContent());

                }
            }
        }catch (SAXParseException e) {
            logger.info("** Parsing error" + ", line "
                    + e.getLineNumber () + ", uri " + e.getSystemId ());
            logger.info(" " + e.getMessage ());

        }catch (SAXException e) {
            Exception x = e.getException ();
            ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
            t.printStackTrace ();
        }

        try {
            // Get the ArrayList of values, password and uuid
            // UUID index 1
            ArrayList<String> values = userList.get(search_user);
            if (values.get(0).equals(search_pw)){
                return true;
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return false;
    }
}
