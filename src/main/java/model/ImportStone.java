package model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;
import org.jsefa.xml.annotation.XmlDataType;
import org.jsefa.xml.annotation.XmlElement;


@Entity
@Table(name = "ImportStones")
@XmlDataType(defaultElementName = "stone")
@CsvDataType()
public class ImportStone implements org.codehaus.jackson.map.JsonSerializableWithType {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
    @CsvField(pos = 1)
    @XmlElement(name = "InvNr1", pos = 1)
	@Column(name = "InvNr")
	private String invNr;

    @CsvField(pos = 2)
    @XmlElement(name = "MusId", pos = 2)
	@Column(name = "MusId")
	private Long musId;

    @CsvField(pos = 3)
    @XmlElement(name = "Link", pos = 3)
	@Column(name = "Link")
	private String link;

    @CsvField(pos = 4)
    @XmlElement(name = "InvNr2", pos = 4)
    @Column(name = "InvNr2")
    private String InvNr2;

    @CsvField(pos = 5)
    @XmlElement(name = "Bild", pos = 5)
    @Column(name = "Bild")
    private String Bild;

    @CsvField(pos = 6)
    @XmlElement(name = "Titel", pos = 6)
    @Column(name = "Titel")
    private String Titel;

    @CsvField(pos = 7)
    @XmlElement(name = "Masze", pos = 7)
    @Column(name = "Masze")
    private String Masze;

    @CsvField(pos = 8)
    @XmlElement(name = "Hoehe", pos = 8)
    @Column(name = "Hoehe")
    private String Hoehe;

    @CsvField(pos = 9)
    @XmlElement(name = "Breite", pos = 9)
    @Column(name = "Breite")
    private String Breite;

    @CsvField(pos = 10)
    @XmlElement(name = "Tiefe", pos = 10)
    @Column(name = "Tiefe")
    private String Tiefe;

    @CsvField(pos = 11)
    @XmlElement(name = "Unit", pos = 11)
    @Column(name = "Unit")
    private String Unit;

    @CsvField(pos = 12)
    @XmlElement(name = "Material", pos = 12)
    @Column(name = "Material")
    private String Material;

    @CsvField(pos = 13)
    @XmlElement(name = "MaterialId", pos = 13)
    @Column(name = "MaterialId")
    private String MaterialId;

    @CsvField(pos = 14)
    @XmlElement(name = "Datierung", pos = 14)
    @Column(name = "Datierung")
    private String Datierung;

    @CsvField(pos = 15)
    @XmlElement(name = "Erdzeitalter", pos = 15)
    @Column(name = "Erdzeitalter")
    private String Erdzeitalter;

    @CsvField(pos = 16)
    @XmlElement(name = "ErdzeitalterId", pos = 16)
    @Column(name = "ErdzeitalterId")
    private String ErdzeitalterId;

    @CsvField(pos = 17)
    @XmlElement(name = "Herkunft", pos = 17)
    @Column(name = "Herkunft")
    private String Herkunft;

    @CsvField(pos = 18)
    @XmlElement(name = "HerkunftCoord", pos = 18)
    @Column(name = "HerkunftCoord")
    private String HerkunftCoord;

    @CsvField(pos = 19)
    @XmlElement(name = "HerkunftId", pos = 19)
    @Column(name = "HerkunftId")
    private String HerkunftId;

    @CsvField(pos = 20)
    @XmlElement(name = "HerkunftGeoId", pos = 20)
    @Column(name = "HerkunftGeoId")
    private String HerkunftGeoId;

    @CsvField(pos = 21)
    @XmlElement(name = "Fundort", pos = 21)
    @Column(name = "Fundort")
    private String Fundort;

    @CsvField(pos = 22)
    @XmlElement(name = "FundortCoord", pos = 22)
    @Column(name = "FundortCoord")
    private String FundortCoord;

    @CsvField(pos = 23)
    @XmlElement(name = "FundortId", pos = 23)
    @Column(name = "FundortId")
    private String FundortId;

    @CsvField(pos = 24)
    @XmlElement(name = "FundortGeoId", pos = 24)
    @Column(name = "FundortGeoId")
    private String FundortGeoId;

    @CsvField(pos = 25)
    @XmlElement(name = "Kommentar", pos = 25)
    @Column(name = "Kommentar")
    private String Kommentar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvNr() {
		return invNr;
	}

	public void setInvNr(String invNr) {
		this.invNr = invNr;
	}

	public Long getMusId() {
		return musId;
	}

	public void setMusId(Long musId) {
		this.musId = musId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}



	public String getInvNr2() {
		return InvNr2;
	}

	public void setInvNr2(String invNr2) {
		InvNr2 = invNr2;
	}

	public String getBild() {
		return Bild;
	}

	public void setBild(String bild) {
		Bild = bild;
	}

	public String getTitel() {
		return Titel;
	}

	public void setTitel(String titel) {
		Titel = titel;
	}

	public String getMasze() {
		return Masze;
	}

	public void setMasze(String masze) {
		Masze = masze;
	}

	public String getHoehe() {
		return Hoehe;
	}

	public void setHoehe(String hoehe) {
		Hoehe = hoehe;
	}

	public String getBreite() {
		return Breite;
	}

	public void setBreite(String breite) {
		Breite = breite;
	}

	public String getTiefe() {
		return Tiefe;
	}

	public void setTiefe(String tiefe) {
		Tiefe = tiefe;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	public String getMaterialId() {
		return MaterialId;
	}

	public void setMaterialId(String materialId) {
		MaterialId = materialId;
	}

	public String getDatierung() {
		return Datierung;
	}

	public void setDatierung(String datierung) {
		Datierung = datierung;
	}

	public String getErdzeitalter() {
		return Erdzeitalter;
	}

	public void setErdzeitalter(String erdzeitalter) {
		Erdzeitalter = erdzeitalter;
	}

	public String getErdzeitalterId() {
		return ErdzeitalterId;
	}

	public void setErdzeitalterId(String erdzeitalterId) {
		ErdzeitalterId = erdzeitalterId;
	}

	public String getHerkunft() {
		return Herkunft;
	}

	public void setHerkunft(String herkunft) {
		Herkunft = herkunft;
	}

	public String getHerkunftCoord() {
		return HerkunftCoord;
	}

	public void setHerkunftCoord(String herkunftCoord) {
		HerkunftCoord = herkunftCoord;
	}

	public String getHerkunftId() {
		return HerkunftId;
	}

	public void setHerkunftId(String herkunftId) {
		HerkunftId = herkunftId;
	}

	public String getHerkunftGeoId() {
		return HerkunftGeoId;
	}

	public void setHerkunftGeoId(String herkunftGeoId) {
		HerkunftGeoId = herkunftGeoId;
	}

	public String getFundort() {
		return Fundort;
	}

	public void setFundort(String fundort) {
		Fundort = fundort;
	}

	public String getFundortCoord() {
		return FundortCoord;
	}

	public void setFundortCoord(String fundortCoord) {
		FundortCoord = fundortCoord;
	}

	public String getFundortId() {
		return FundortId;
	}

	public void setFundortId(String fundortId) {
		FundortId = fundortId;
	}

	public String getFundortGeoId() {
		return FundortGeoId;
	}

	public void setFundortGeoId(String fundortGeoId) {
		FundortGeoId = fundortGeoId;
	}

	public String getKommentar() {
		return Kommentar;
	}

	public void setKommentar(String kommentar) {
		Kommentar = kommentar;
	}

	@Override
	public void serialize(JsonGenerator jgen, SerializerProvider sprov)
			throws IOException, JsonProcessingException {
		
		jgen.writeStartObject();
		
		jgen.writeObjectField("id", getId());
		jgen.writeEndObject();
		

		
	}

	@Override
	public void serializeWithType(JsonGenerator arg0, SerializerProvider arg1,
			TypeSerializer arg2) throws IOException, JsonProcessingException {
		serialize(arg0, arg1);
	}
	
}
