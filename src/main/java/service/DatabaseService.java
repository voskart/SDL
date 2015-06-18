package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

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
	Context context;
    private static final Logger log = Logger.getLogger(String.valueOf(DatabaseService.class));
	@Autowired 
	private ServletContext servletContext;

	public void startDB() throws Exception {

	    // Create database context
	    context = new Context();

	    System.out.println("=== CreateCollection ===");

	    new Set("CREATEFILTER", "*.xml").execute(context);


	    new CreateDB("Database").execute(context);
	    
	    
	    
	    
	    System.out.println("\n* Create a collection.");
		ServletContextResource resource = new ServletContextResource(servletContext, 
			    "/WEB-INF/content/outpput.xml");
		InputStream inputStream = resource.getInputStream();
	    Add addx =new Add("outpput.xml");
	    addx.setInput(inputStream);
	    addx.execute(context);
		
		

		ServletContextResource resourcex = new ServletContextResource(servletContext, 
			    "/WEB-INF/content/users.xml");
		
		InputStream inputStreamx = resourcex.getInputStream();
	    Add add = new Add("users.xml");
	    add.setInput(inputStreamx);
	    add.execute(context);

	    new Optimize().execute(context);
		
		

	    // Show information on the currently opened database
	    System.out.println("\n* Show database information:");

	    System.out.println(new InfoDB().execute(context));


	}

	public void stopDB() throws IOException {
	    // Create database context
	    context = new Context();	    // Drop the database
	    System.out.println("\n* Drop the collection.");

	    new DropDB("Collection").execute(context);

	    // Close the database context
	    context.close();
	}

	public String getAllUsernames() throws BaseXException{
	    
	    return (new XQuery(
	            "for $doc in collection()" +
	            "let $file-path := base-uri($doc)" +
	            "where ends-with($file-path, 'users.xml')" +
	            "return //users/user/username"
	        ).execute(context));
	}

}
