package CSV2XML;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import model.Stone;

import org.jsefa.xml.XmlIOFactory;
import org.jsefa.xml.XmlSerializer;
import org.jsefa.xml.namespace.QName;

import com.opencsv.CSVReader;

public class CsvToXml {     

    public static void main(String[] args) {

		List<String[]> str = null;
		try {
			str = stringWriter("./hackathon2015-daten-stadtmuseumberlin.csv");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int userId = 0;
    	List<Stone> list = new ArrayList<Stone>();
    	boolean header = true;
    	for (String[] s:str){
    		if(header){
    			// sogt dafür, dass aus dem Header kein Objekt erzeigt wird.
    			header = false;
    		}else{
    			Stone stone = Stone.create(s);
    			stone.setId(userId++);
    			list.add(stone);    			
    		}
    	}
    	
    	XmlSerializer serializer = XmlIOFactory.createFactory(Stone.class).createSerializer();
    	
    	StringWriter writer = new StringWriter();
    	serializer.open(writer);
    	serializer.getLowLevelSerializer().writeXmlDeclaration("1.0", "UTF-8");
    	serializer.getLowLevelSerializer().writeStartElement(QName.create("stones"));
    	// call serializer.write for every object to serialize
    	for (Stone stone:list){
    		serializer.write(stone);
    	}
    	serializer.getLowLevelSerializer().writeEndElement();
    	serializer.close(true);
    	
    	try {
        	FileWriter fw = new FileWriter("./outpput_test.xml");
			fw.write(writer.toString());
        	fw.close();
        	writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    public static List<String[]> stringWriter(String fileName) throws IOException {

        CSVReader reader = new CSVReader(new FileReader(fileName));
        List<String[]> myEntries = reader.readAll();

        return myEntries;
    }
    
    
}