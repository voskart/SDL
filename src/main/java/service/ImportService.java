package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.servlet.ServletContext;

import model.ImportStone;

import org.apache.log4j.Logger;
import org.jsefa.xml.XmlDeserializer;
import org.jsefa.xml.XmlIOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.ServletContextResource;

import dao.ImportStoneDao;


@Service
public class ImportService {
	

	private static Logger log = Logger.getLogger(ImportService.class);
	@Autowired
	private ImportStoneDao importStoneDao;
	@Autowired 
	private ServletContext servletContext;
	@Transactional
	public void importtoDB() throws MalformedURLException, IOException{
		
		/*FileSystemResource resource = new FileSystemResource("/WEB-INF/resource/hackathon2015-daten-stadtmuseumberlin.csv");
		File file = resource.getFile();

		byte[] bytes;
		
		*/
		
		 InputStream inputStream = null;
		try {
			ServletContextResource resource = new ServletContextResource(servletContext, 
				    "/WEB-INF/content/outpput.xml");
			
            //inputStream = servletContext.getResourceAsStream("/WEB-INF/resource/hackathon2015-daten-stadtmuseumberlin.csv");
            File file = resource.getFile();
			inputStream=resource.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			StringWriter writer = new StringWriter();
            /*
			bytes = Files.readAllBytes(file.toPath());
			writer.write( new String(bytes,"UTF-8"));*/
			//writer.write(bufferedReader.readLine());
			writer.write(new Scanner(file).useDelimiter("\\Z").next());
			
			XmlDeserializer deserializer = XmlIOFactory.createFactory(ImportStone.class).createDeserializer();
			StringReader reader = new StringReader(writer.toString());
			deserializer.open(reader);

			while (deserializer.hasNext()) {
			    ImportStone p = deserializer.next();
			    importStoneDao.persist(p);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
