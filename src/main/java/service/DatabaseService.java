package service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.basex.BaseXServer;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.server.ClientQuery;
import org.basex.server.ClientSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

import controller.RegistrationController;

@Service
public class DatabaseService {
	BaseXServer server;
    private static final Logger log = Logger.getLogger(String.valueOf(DatabaseService.class));
	@Autowired 
	private ServletContext servletContext;

	public void startDB() throws Exception {
		log.info("=== ServerCommands ===");

		// Start server on default port 1984
		server = new BaseXServer();

		// Create a client session with host name, port, user name and password
		log.info("\n* Create a client session.");

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");

		// Create a database
		log.info("\n* Create a database.");
		ServletContextResource resource = new ServletContextResource(servletContext, 
			    "/WEB-INF/content/outpput.xml");
		
        File file = resource.getFile();
		InputStream inputStream=resource.getInputStream();
		CreateDB createDb = new CreateDB("input");
		createDb.setInput(inputStream);
		session.execute(createDb);
		

		// Faster version: specify an output stream and run a query
		log.info("\n* Run a query (faster):");
		ClientQuery query = session.query("//stones/stone/Material");
		log.info(query.execute());

		session.close();

	}

	public void stopDB() throws IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		// Drop the database
		log.info("\n* Close and drop the database.");

		session.execute(new DropDB("input"));

		// Stop the server
		log.info("\n* Stop the server.");

		server.stop();
	}

}
