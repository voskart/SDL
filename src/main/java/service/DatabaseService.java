package service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQItem;

import model.Rating;
import model.Stone;
import model.User;
import net.xqj.basex.BaseXXQDataSource;

import org.apache.log4j.Logger;
import org.basex.BaseXServer;
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
		
		importRatings(userResource, xqc2, xqItem2);
		
		xqe.executeCommand("SET WRITEBACK true");

		xqc.close();

	}

	/**
	 * Importiert die Datei ratings.xml in die basex Datenbank.
	 * @param userResource
	 * @param xqc2
	 * @param xqItem2
	 * @throws XQException
	 * @throws IOException
	 */
	private void importRatings(ServletContextResource userResource,
			XQConnection2 xqc2, XQItem xqItem2) throws XQException, IOException {
		ServletContextResource ratingResource = new ServletContextResource(
				servletContext, "/WEB-INF/content/ratings.xml");

		XQItem xqItem3 = xqc2.createItemFromDocument(
				ratingResource.getInputStream(), null, null);
		xqc2.insertItem("ratings.xml", xqItem3, null);
	}

	public void closeBasexDatabase() throws IOException { // Create

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		session.execute("drop database xmlDB");
		session.close();
	}

//	public String getUserPasswordHash(String username) throws XQException,
//			IOException {
//		ClientSession session = new ClientSession("localhost", 1984, "admin",
//				"admin");
//		String data = session.execute("open xmlDB");
//		session.execute("SET WRITEBACK TRUE");
//		String getUserPasswordHashResult = session
//				.execute("xquery data(//users/user[username eq '" + username
//						+ "']/password)");
//		session.close();
//		LOGGER.info("### getUserPasswordHashResult: "
//				+ getUserPasswordHashResult);
//		return getUserPasswordHashResult;
//	}
	
	public User getUserByName(String username) throws XQException, IOException {
		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		data = session.execute("xquery //users/user[username='" + username + "']");
		session.close();
		XmlDeserializer deserializer = XmlIOFactory.createFactory(User.class)
				.createDeserializer();
		StringReader reader = new StringReader(data);
		deserializer.open(reader);
		if (deserializer.hasNext()) {
			return deserializer.next();
		} else {
			return null;
		}
	}

	public List<Stone> getAllStones() throws XQException, IOException {
		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
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
//			System.out.println(p.toString());
		}
		return stones;
	}

	public Stone getStonebyId(Integer id) throws XQException, IOException {
		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		data = session.execute("xquery //stones/stone[Id='" + id + "']");
		session.close();
		XmlDeserializer deserializer = XmlIOFactory.createFactory(Stone.class)
				.createDeserializer();
		StringReader reader = new StringReader(data);
		deserializer.open(reader);
		List<Stone> stones = new ArrayList<Stone>();
		if (deserializer.hasNext()) {
			Stone p = deserializer.next();
			return p;
		} else {
			return null;
		}
	}

	// Returns all the usernames in a list (all usernames in form of a list)
	public List<User> getAllUsers() throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		data = session.execute("xquery //users");
		XmlDeserializer deserializer = XmlIOFactory.createFactory(User.class)
				.createDeserializer();
		StringReader reader = new StringReader(data);
		deserializer.open(reader);
		List<User> users = new ArrayList<User>();
		while (deserializer.hasNext()) {
			User u = deserializer.next();
			users.add(u);
			System.out.println(u.toString());
		}
		session.close();
		return users;
	}

	public String insertNewUserData(User user) throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		// System.out.println(session.execute("info"));
		// XmlSerializer serializer =
		// XmlIOFactory.createFactory(User.class).createSerializer();
		// StringWriter writer = new StringWriter();
		// serializer.open(writer);
		// serializer.write(user);
		// serializer.close(true);
		// String userXML = writer.toString();
		// writer.close();
		// System.out.println(userXML);
		// data = session.execute("xquery let $up := <user>\n" +
		// "  <username>hallo</username>\n" +
		// "  <password>bla</password>\n" +
		// "  <uuid>111</uuid>\n" +
		// "</user> return insert node $up as last into /users");
		data = session.execute("xquery let $up :=  <user>" + "<username>"
				+ user.getUsername() + "</username>" + "<password>"
				+ user.getPassword() + "</password>" + "<uuid>" + user.getId()
				+ "</uuid>"
				+ "</user> return insert node $up as last into /users");
		session.close();
		return data;
	}

	public Integer getLastUserID() throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		System.out.println(session.execute("set writeback true"));
		data = session.execute("xquery data((//users/user/uuid)[last()])");
		session.close();
		return Integer.parseInt(data);
	}

	public void exportData(String path) throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		System.out.println(session.execute("set writeback true"));
		data = session.execute("export " + path);
		session.close();
	}

	public List<Rating> getAllRatings() throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		data = session.execute("xquery //ratings");
		XmlDeserializer deserializer = XmlIOFactory.createFactory(Rating.class)
				.createDeserializer();
		StringReader reader = new StringReader(data);
		deserializer.open(reader);
		List<Rating> users = new ArrayList<Rating>();
		while (deserializer.hasNext()) {
			Rating u = deserializer.next();
			users.add(u);
			System.out.println(u.toString());
		}
		session.close();
		return users;
	}

	public String insertRating(Rating rating) throws XQException, IOException {

		ClientSession session = new ClientSession("localhost", 1984, "admin",
				"admin");
		String data = session.execute("open xmlDB");
		session.execute("SET WRITEBACK TRUE");
		data = session.execute("xquery let $up :=  <rating>" + "<userid>"
				+ rating.getUserId() + "</userid>" + "<stoneid>"
				+ rating.getStoneId() + "</stoneid>" + "<voting>"
				+ rating.getVoting() + "</voting>"
				+ "</rating> return insert node $up as last into /ratings");
		session.close();
		return data;
	}
}
