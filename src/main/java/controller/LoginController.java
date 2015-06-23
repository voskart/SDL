package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/login")
public class LoginController {

	@Autowired
    private DatabaseService dbservice;
	
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

        boolean tmp_bool = false;
        try {
            tmp_bool = validateUserInXMLDB(tmp_user);
        } catch (BaseXException e) {
            e.printStackTrace();
        }

        // Check if the user is in the XML file
        if (tmp_bool){
            model.addAttribute("username", username);
            return "HotOrNot";
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

    private boolean validateUserInXMLDB(User user) throws BaseXException {

        // Get the dbservice instance
        // DatabaseService dbservice = new DatabaseService();
//        DatabaseService dbservice = DatabaseService.getInstance();
        String tmpPass = user.getPassword();
        String userHash = null;
        try{
        	userHash = dbservice.getUserPasswordHash(user.getUsername());
        }catch (NullPointerException e){
            logger.info(e.getMessage());
        }
            logger.info(user.getPassword() + " " + tmpPass);

        try {
            // Check if the password in the XML equals the one in the passed form
            if (user.getPassword().equals(userHash)){
                return true;
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return false;
    }
}
