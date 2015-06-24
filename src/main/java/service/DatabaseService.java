package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQSequenceType;

import model.Stone;
import model.User;
import net.xqj.basex.BaseXXQDataSource;

import org.apache.log4j.Logger;
import org.basex.BaseXServer;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.Export;
import org.basex.core.cmd.InfoDB;
import org.basex.core.cmd.Optimize;
import org.basex.core.cmd.Set;
import org.basex.core.cmd.XQuery;
import org.basex.server.ClientSession;
import org.jsefa.xml.XmlDeserializer;
import org.jsefa.xml.XmlIOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

import com.xqj2.XQConnection2;

@Service
public class DatabaseService {

	public DatabaseService() {
	}

	private static final Logger LOGGER = Logger.getLogger(String
			.valueOf(DatabaseService.class));

	@Autowired
	private ServletContext servletContext = null;

	public XQDataSource ds;

	public void openBasexDatabase() throws Exception {

		BaseXServer server = new BaseXServer();

		ds = new BaseXXQDataSource();
		ds.setProperty("serverName", "localhost");
		ds.setProperty("port", "1984");
		ds.setProperty("user", "admin");
		ds.setProperty("password", "admin");

		XQConnection xqc = ds.getConnection();
		XQExpression xqe = xqc.createExpression();
		xqe.executeCommand("SET WRITEBACK true");
		xqe.executeCommand("CREATE DB xmlDB");
		ds.setProperty("databaseName", "xmlDB");

		ServletContextResource userResource = new ServletContextResource(
				servletContext, "/WEB-INF/content/users.xml");

		ServletContextResource resource = new ServletContextResource(
				servletContext, "/WEB-INF/content/outpput.xml");

		XQConnection2 xqc2 = (XQConnection2) ds.getConnection();
		XQItem xqItem = xqc2.createItemFromDocument(resource.getInputStream(),
				null, null);
		xqc2.insertItem("stones.xml", xqItem, null);
		XQItem xqItem2 = xqc2.createItemFromDocument(
				userResource.getInputStream(), null, null);
		xqc2.insertItem("users.xml", xqItem2, null);

		xqe.executeCommand("SET WRITEBACK true");
		getAllStones();
		xqc.close();


	}

	
	 public void closeBasexDatabase() throws IOException { // Create

		ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
		session.execute("drop database xmlDB");
		session.close();
	 }
	 
	public String getUserPasswordHash(String username) throws XQException, IOException {
		ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		String getUserPasswordHashResult= session.execute("xquery data(//users/user[username eq '" + username
						+ "']/password)");
		session.close();
		LOGGER.info("### getUserPasswordHashResult: "
				+ getUserPasswordHashResult);
		return getUserPasswordHashResult;
	}

	public List<Stone> getAllStones() throws XQException, IOException {
		ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		data = session.execute("xquery //stones");
		session.close();
		XmlDeserializer deserializer = XmlIOFactory.createFactory(Stone.class)
				.createDeserializer();
		StringReader reader = new StringReader(data);
		deserializer.open(reader);
		List<Stone> stones = new ArrayList<Stone>();
		while (deserializer.hasNext()) {
			Stone p = deserializer.next();
			stones.add(p);
			System.out.println(p.toString());
		}
		return stones;
	}

	// Returns all the usernames in a list (all usernames in form of a list)
	public List<String> getAllUsers() throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		data = session.execute("xquery data(//users/user/uuid)");
		String userArray[] = data.split("\\r?\\n");
		// Convert to an ArrayList just for Benny <3
		List<String> userList = Arrays.asList(userArray);
		session.close();
		return userList;
	}

	public String insertNewUserData(User user) throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		System.out.println(session.execute("info"));	
		data = session.execute("xquery let $up :=  <user>" + "<username>"
						+ user.getUsername() + "</username>" + "<password>"
						+ user.getPassword() + "</password>" + "<uuid>" + user.getId()
						+ "</uuid>" + "</user> return insert node $up as last into /users");
		session.close();
		return data;
	}

	public Integer getLastUserID() throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
		String data = session.execute("open xmlDB");
		System.out.println(session.execute("set writeback true"));
		data = session.execute("xquery data((//users/user/uuid)[last()])");
		session.close();
		return Integer.parseInt(data);
	}

	public void exportData(String path) throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin", "admin");
		String data = session.execute("open xmlDB");
		System.out.println(session.execute("set writeback true"));
		data = session.execute("export "+path);
		session.close();
	}
}
