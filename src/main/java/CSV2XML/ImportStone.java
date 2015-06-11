package CSV2XML;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.xml.annotation.XmlDataType;
import org.jsefa.xml.annotation.XmlElement;

@XmlDataType(defaultElementName = "stone")
@CsvDataType()
public class ImportStone {

    @CsvField(pos = 1)
    @XmlElement(name = "InvNr1", pos = 1)
    String InvNr1;

    @CsvField(pos = 2)
    @XmlElement(name = "MusId", pos = 2)
    String MusId;

    @CsvField(pos = 3)
    @XmlElement(name = "Link", pos = 3)
    String Link;

    @CsvField(pos = 4)
    @XmlElement(name = "InvNr2", pos = 4)
    String InvNr2;

    @CsvField(pos = 5)
    @XmlElement(name = "Bild", pos = 5)
    String Bild;

    @CsvField(pos = 6)
    @XmlElement(name = "Titel", pos = 6)
    String Titel;

    @CsvField(pos = 7)
    @XmlElement(name = "Masze", pos = 7)
    String Masze;

    @CsvField(pos = 8)
    @XmlElement(name = "Hoehe", pos = 8)
    String Hoehe;

    @CsvField(pos = 9)
    @XmlElement(name = "Breite", pos = 9)
    String Breite;

    @CsvField(pos = 10)
    @XmlElement(name = "Tiefe", pos = 10)
    String Tiefe;

    @CsvField(pos = 11)
    @XmlElement(name = "Unit", pos = 11)
    String Unit;

    @CsvField(pos = 12)
    @XmlElement(name = "Material", pos = 12)
    String Material;

    @CsvField(pos = 13)
    @XmlElement(name = "MaterialId", pos = 13)
    String MaterialId;

    @CsvField(pos = 14)
    @XmlElement(name = "Datierung", pos = 14)
    String Datierung;

    @CsvField(pos = 15)
    @XmlElement(name = "Erdzeitalter", pos = 15)
    String Erdzeitalter;

    @CsvField(pos = 16)
    @XmlElement(name = "ErdzeitalterId", pos = 16)
    String ErdzeitalterId;

    @CsvField(pos = 17)
    @XmlElement(name = "Herkunft", pos = 17)
    String Herkunft;

    @CsvField(pos = 18)
    @XmlElement(name = "HerkunftCoord", pos = 18)
    String HerkunftCoord;

    @CsvField(pos = 19)
    @XmlElement(name = "HerkunftId", pos = 19)
    String HerkunftId;

    @CsvField(pos = 20)
    @XmlElement(name = "HerkunftGeoId", pos = 20)
    String HerkunftGeoId;

    @CsvField(pos = 21)
    @XmlElement(name = "Fundort", pos = 21)
    String Fundort;

    @CsvField(pos = 22)
    @XmlElement(name = "FundortCoord", pos = 22)
    String FundortCoord;

    @CsvField(pos = 23)
    @XmlElement(name = "FundortId", pos = 23)
    String FundortId;

    @CsvField(pos = 24)
    @XmlElement(name = "FundortGeoId", pos = 24)
    String FundortGeoId;

    @CsvField(pos = 25)
    @XmlElement(name = "Kommentar", pos = 25)
    String Kommentar;
    
    
    public static ImportStone create(String[] list){
    	ImportStone stone= new ImportStone();
    	stone.InvNr1=list[0];
    	stone.MusId=list[1];
    	stone.Link=list[2];
    	stone.InvNr2=list[3];
    	stone.Bild=list[4];
    	stone.Titel=list[5];
    	stone.Masze=list[6];
    	stone.Hoehe=list[7];
    	stone.Breite=list[8];
    	stone.Tiefe=list[9];
    	stone.Unit=list[10];
    	stone.Material=list[11];
    	stone.MaterialId=list[12];
    	stone.Datierung=list[13];
    	stone.Erdzeitalter=list[14];
    	stone.ErdzeitalterId=list[15];
    	stone.Herkunft=list[16];
    	stone.HerkunftCoord=list[17];
    	stone.HerkunftId=list[18];
    	stone.HerkunftGeoId=list[19];
    	stone.Fundort=list[20];
    	stone.FundortCoord=list[21];
    	stone.FundortId=list[22];
    	stone.FundortGeoId=list[23];
    	stone.Kommentar=list[24];
    	return stone;
    }


	public ImportStone() {
	}

}
