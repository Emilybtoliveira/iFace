package br.iface.classes;
import java.util.List;
import java.util.ArrayList;

public class User {
    private String login;
    private String password;
    private String name;
    private List friends_requests;

    public User(){}

    public User(String login, String password, String name){
        this.login = login;
        this.password = password;
        this.name = name;
        this.friends_requests = new ArrayList();
    }

    public String getLogin(){
        return this.login;
    }

    public boolean setLogin(String login){
        this.login = login;
        return true;
    }

    public String getPass(){
        return this.password;
    }

    public boolean setPass(String password){
        this.password = password;
        return true;
    }

    public String getName(){
        return this.name;
    }

    public boolean setName(String name){
        this.name = name;
        return true;
    }

    public List getFriends_requests() {
        return friends_requests;
    }

    public void setFriends_requests(String friend_username) {
        this.friends_requests.add(friend_username);
    }
}
