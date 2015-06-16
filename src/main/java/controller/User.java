package controller;

/**
 * Created by voskart on 11.06.15.
 */
public class User{

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

    public String getUuid() { return this.uuid; }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
