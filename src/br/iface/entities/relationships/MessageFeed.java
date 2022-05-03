package br.iface.entities.relationships;

import br.iface.entities.*;

import java.util.ArrayList;
import java.util.List;

public class MessageFeed implements Feed<TextMessage> {
    private User user1;
    private User user2;
    private List<TextMessage> textMessages;

    public MessageFeed(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
        this.textMessages = new ArrayList<TextMessage>();
    }

    public void addNewMessage(TextMessage new_text_msg){
        this.textMessages.add(new_text_msg);
    };

    public List<TextMessage> getMessages(){
        return this.textMessages;
    }

    public void removeMessagesFromUser(User user){
        return;
    }

    public User getUser1() {
        return this.user1;
    }

    public User getUser2() {
        return this.user2;
    }
}
