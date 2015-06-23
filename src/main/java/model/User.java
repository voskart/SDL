package model;

import interfaces.IRateable;

/**
 * Created by voskart on 11.06.15.
 */
public class User implements IRateable {

    private String username;
    private String password;
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
}
