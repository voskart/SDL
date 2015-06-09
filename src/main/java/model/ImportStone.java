package model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.hibernate.proxy.HibernateProxy;


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
