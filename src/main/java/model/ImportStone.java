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


@Entity
@Table(name = "ImportStones")
public class ImportStone implements org.codehaus.jackson.map.JsonSerializableWithType {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "InvNr")
	private String invNr;
	
	@Column(name = "MusId")
	private Long musId;

	@Column(name = "Link")
	private String link;

    @Column(name = "InvNr2")
    private String InvNr2;

    @Column(name = "Bild")
    private String Bild;

    @Column(name = "Titel")
    private String Titel;

    @Column(name = "Masze")
    private String Maﬂe;

    @Column(name = "Hoehe")
    private String Hoehe;

    @Column(name = "Breite")
    private String Breite;

    @Column(name = "Tiefe")
    private String Tiefe;

    @Column(name = "Unit")
    private String Unit;

    @Column(name = "Material")
    private String Material;

    @Column(name = "MaterialId")
    private String MaterialId;

    @Column(name = "Datierung")
    private String Datierung;

    @Column(name = "Erdzeitalter")
    private String Erdzeitalter;

    @Column(name = "ErdzeitalterId")
    private String ErdzeitalterId;

    @Column(name = "Herkunft")
    private String Herkunft;

    @Column(name = "HerkunftCoord")
    private String HerkunftCoord;

    @Column(name = "HerkunftId")
    private String HerkunftId;

    @Column(name = "HerkunftGeoId")
    private String HerkunftGeoId;

    @Column(name = "Fundort")
    private String Fundort;

    @Column(name = "FundortCoord")
    private String FundortCoord;

    @Column(name = "FundortId")
    private String FundortId;

    @Column(name = "FundortGeoId")
    private String FundortGeoId;

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

	public String getMaﬂe() {
		return Maﬂe;
	}

	public void setMaﬂe(String maﬂe) {
		Maﬂe = maﬂe;
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
