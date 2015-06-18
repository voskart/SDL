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



@Service
public class ImportService {
	

	private static Logger log = Logger.getLogger(ImportService.class);
	@Autowired 
	private ServletContext servletContext;


}
