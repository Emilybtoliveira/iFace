package br.iface.entities;

public class TextMessage extends Message {

    public TextMessage(User sender, User recipient, String content){
        super(sender, recipient, content);
    }

    @Override
    public String toString(){
        return ("["+this.timestamp+"] "+this.sender.getLogin() + ": " + this.content);
    }
}
