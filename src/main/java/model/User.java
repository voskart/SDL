package model;

import interfaces.IRateable;
import org.jsefa.xml.annotation.XmlDataType;
import org.jsefa.xml.annotation.XmlElement;

/**
 * Created by voskart on 11.06.15.
 */
@XmlDataType(defaultElementName = "user")
public class User implements IRateable {

    @XmlElement(name = "username", pos = 1)
    private String username;

    @XmlElement(name = "password", pos = 2)
    private String password;

    @XmlElement(name = "uuid", pos = 3)
    private String uuid;

    public User(String username, String password, String uuid){
        this.username = username;
        this.password = password;
        this.uuid = uuid;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getID() {
        return this.uuid;
    }

    @Override
    public String toString(){ return this.username + " " + this.getPassword() + " " + this.getID();}

    public User(){}
}
