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
        User tmp_user = new User(username, passwordHash);

        logger.info("username: "+ username+", password: " + passwordHash);

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

    private static String encryptPassword(String password)
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
        Map<String, String> userList = new HashMap<String, String>();
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

            for(int s=0; s<listOfUsers.getLength() ; s++){

                Node firstPersonNode = listOfUsers.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){

                    String tmp_username;
                    String tmp_password;

                    Element firstPersonElement = (Element)firstPersonNode;

                    NodeList usernameList = firstPersonElement.getElementsByTagName("username");
                    Element firstNameElement = (Element)usernameList.item(0);

                    NodeList UNList = firstNameElement.getChildNodes();
                    tmp_username = ((Node)UNList.item(0)).getNodeValue().trim();

                    NodeList passwordList = firstPersonElement.getElementsByTagName("password");
                    Element passwordElement = (Element)passwordList.item(0);

                    NodeList PWList = passwordElement.getChildNodes();
                    tmp_password = ((Node)PWList.item(0)).getNodeValue().trim();

                    userList.put(tmp_username, tmp_password);
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
            String password = userList.get(search_user);
            if (password.equals(search_pw)){
                return true;
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return false;
    }
}
