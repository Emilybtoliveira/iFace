package br.iface.classes;
import java.util.List;
import java.util.ArrayList;

public class User {
    private String login;
    private String password;
    private String name;
    private List friends_requests;
    private List my_friends;
    private List chats;

    public User(){}

    public User(String login, String password, String name){
        this.login = login;
        this.password = password;
        this.name = name;
        this.friends_requests = new ArrayList();
        this.my_friends = new ArrayList();
        this.chats = new ArrayList();
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

    public List getFriendsRequests() {
        return this.friends_requests;
    }

    public void setAFriendsRequest(User friend) {
        this.friends_requests.add(friend);
    }

    public void removeFriendRequest(User friend){
        this.friends_requests.remove(friend);
    }

    public List getMyfriends() {
        return this.my_friends;
    }

    public void setMyFriends(User friend_username) {
        this.my_friends.add(friend_username);
    }

    public List getChats(){
        return this.chats;
    }

    public void setNewChat(MessageHistory messageHistory){
        this.chats.add(messageHistory);
    }
}
