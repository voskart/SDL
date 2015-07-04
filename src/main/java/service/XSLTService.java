package service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.ServletContextResource;

@Service
public class XSLTService {
	
	@Autowired
	private ServletContext servletContext;
	
	public String getStoneTable(){
		StringWriter writer=new StringWriter();

		ServletContextResource xmlResource = new ServletContextResource(
				servletContext, "/WEB-INF/content/outpput.xml");
		ServletContextResource sheetResource = new ServletContextResource(
				servletContext, "/WEB-INF/content/stoneTable.xsl");
		
		File xmlDocument;
		File stylesheet;
		try {
			xmlDocument = xmlResource.getFile();
			stylesheet = sheetResource.getFile();
		} catch (IOException e) {
			return e.getMessage();
		}
		
		
	    TransformerFactory factory=TransformerFactory.newInstance();
	    Transformer transformer;
		try {
			transformer = factory.newTransformer(new StreamSource(stylesheet));
		    transformer.transform(new StreamSource(xmlDocument),new StreamResult(writer));
		//Evtl personalisierte Nachricht?
		} catch (TransformerConfigurationException e) {
			return e.getMessage();
		} catch (TransformerException e) {
			return e.getMessage();
		}
	    return writer.toString();
	}
}
