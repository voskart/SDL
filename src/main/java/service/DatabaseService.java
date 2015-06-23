package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import model.User;
import org.apache.log4j.Logger;
import org.basex.BaseXServer;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.Delete;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.InfoDB;
import org.basex.core.cmd.Optimize;
import org.basex.core.cmd.Set;
import org.basex.core.cmd.XQuery;
import org.basex.server.ClientQuery;
import org.basex.server.ClientSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

import controller.RegistrationController;

@Service
public class DatabaseService {

    // TODO DBService as Singleton
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(DatabaseService.class));

    @Autowired
    private ServletContext servletContext = null;

    Context context = new Context();

    // TODO Singleton

    public void openBasexDatabase() throws IOException {

        LOGGER.info("=== CreateCollection ===");

        new Set("CREATEFILTER", "*.xml").execute(context);


        new CreateDB("Database").execute(context);


        LOGGER.info("\n* Create a collection.");
        ServletContextResource resource = new ServletContextResource(servletContext,
                "/WEB-INF/content/outpput.xml");
        InputStream inputStream = resource.getInputStream();
        Add addx = new Add("outpput.xml");
        addx.setInput(inputStream);
        addx.execute(context);


        ServletContextResource userResource = new ServletContextResource(servletContext,
                "/WEB-INF/content/users.xml");

        InputStream inputStreamUsers = null;
        try {
            inputStreamUsers = userResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Add add = new Add("users.xml");
        add.setInput(inputStreamUsers);
        add.execute(context);

        new Optimize().execute(context);


        // Show information on the currently opened database
        LOGGER.info("\n* Show database information:");

        LOGGER.info(new InfoDB().execute(context));
    }

	public void closeBasexDatabase() throws BaseXException {
	    // Create database context
	    context = new Context();	    // Drop the database
	    System.out.println("\n* Drop the collection.");

	    new DropDB("Collection").execute(context);

	    // Close the database context
	    context.close();
	}

	public String getUserPasswordHash(String username) throws BaseXException{
	    
	    return(new XQuery(
	            "for $doc in collection()" +
	            "let $file-path := base-uri($doc)" +
	            "where ends-with($file-path, 'users.xml')" +
                "return data(//users/user[username eq '" + username + "']/password)"
	            ).execute(context));
	}

    public void insertNewUserData(User user){
        // TODO
    }

}
