package br.iface.classes;

import java.util.ArrayList;
import java.util.List;

public class MessageHistory {
    private User user1;
    private User user2;
    private List history;

    public MessageHistory(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
        this.history = new ArrayList();
    }

    public boolean newMessage(Message message){
        return this.history.add(message);
    }

    public List getHistory(){
        return this.history;
    }

    public User getUser1() {
        return this.user1;
    }

    public User getUser2() {
        return this.user2;
    }
}
