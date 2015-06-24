package service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import model.User;

import org.apache.log4j.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.InfoDB;
import org.basex.core.cmd.Optimize;
import org.basex.core.cmd.Set;
import org.basex.core.cmd.XQuery;
import org.jsefa.xml.XmlDeserializer;
import org.jsefa.xml.XmlIOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

import CSV2XML.ImportStone;

@Service
public class DatabaseService {

	public DatabaseService() {
		this.context = new Context();
	}

	private static final Logger LOGGER = Logger.getLogger(String
			.valueOf(DatabaseService.class));

	@Autowired
	private ServletContext servletContext = null;

	Context context = null;

	public void openBasexDatabase() throws IOException {

		LOGGER.info("=== CreateCollection ===");

		new Set("CREATEFILTER", "*.xml").execute(context);

		new DropDB("Database").execute(context);
		new CreateDB("Database").execute(context);

		LOGGER.info("\n* Create a collection.");
		ServletContextResource resource = new ServletContextResource(
				servletContext, "/WEB-INF/content/outpput.xml");
		InputStream inputStream = resource.getInputStream();
		Add addx = new Add("outpput.xml");
		addx.setInput(inputStream);
		addx.execute(context);
		
		new Optimize().execute(context);

		ServletContextResource userResource = new ServletContextResource(
				servletContext, "/WEB-INF/content/users.xml");

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

        User user = new User();
        user.setUsername("user2");
        LOGGER.info(insertNewUserData(user));
        LOGGER.info(getAllUsers());
		LOGGER.info(new InfoDB().execute(context));

	}

	public void closeBasexDatabase() throws BaseXException {
		// Create database context
		context = new Context(); // Drop the database
		System.out.println("\n* Drop the collection.");

		new DropDB("Database").execute(context);

		// Close the database context
		context.close();
	}

	public String getUserPasswordHash(String username) throws BaseXException {
		String getUserPasswordHashResult = new XQuery("for $doc in collection('Database')"
				+ " let $file-path := base-uri($doc)"
				+ " where ends-with($file-path, 'users.xml')"
				+ " return data(//users/user[username eq '" + username
				+ "']/password)").execute(context);
		LOGGER.info("### getUserPasswordHashResult: " + getUserPasswordHashResult);
		return getUserPasswordHashResult;
	}

	
	public List<ImportStone> getAllStones() throws BaseXException {
		String data = (new XQuery("for $doc in collection('Database')"
				+ " let $file-path := base-uri($doc)"
				+ " where ends-with($file-path, 'users.xml')"
				+ " return //stones").execute(context));
		XmlDeserializer deserializer = XmlIOFactory.createFactory(ImportStone.class).createDeserializer();
		StringReader reader = new StringReader(data);
		deserializer.open(reader);
		List<ImportStone> stones = new ArrayList<ImportStone>();
		while (deserializer.hasNext()) {
		    ImportStone p = deserializer.next();
		    stones.add(p);
		    System.out.println(p.toString());
		}
		return stones;
	}

    // Returns all the usernames in a list (all usernames in form of a list)
    public List<String> getAllUsers() throws BaseXException {
        String data = (new XQuery("for $doc in collection('Database')"
                + " let $file-path := base-uri($doc)"
                + " where ends-with($file-path, 'users.xml')"
                + " return data(//users/user/username)").execute(context));

        // If more values needed: return concat data(//users/user/username) + ' ' + data(//users/user/uuid)
        String userArray[] = data.split("\\r?\\n");
        // Convert to an ArrayList just for Benny <3
        List<String> userList = Arrays.asList(userArray);
        return userList;
    }
	
	public String insertNewUserData(User user) throws BaseXException {
        String data = (new XQuery("for $doc in collection('Database')"
                + " let $file-path := base-uri($doc)"
                + " where ends-with($file-path, 'users.xml')"
                + " return insert node <user>" +
                "<username>" + user.getUsername() + "</username>" +
                "<password>" + user.getPassword() + "</password>" +
                "<uuid>" + user.getId() + "</uuid>" +
                "</user> as last into //users").execute(context));

        return data;
	}
}
