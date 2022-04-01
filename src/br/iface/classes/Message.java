package br.iface.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Message {
    private User sender;
    private User recipient;
    private String content;
    private String timestamp;

    public Message(){}

    public Message(User sender, User recipient, String content){
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.timestamp = dtf.format(LocalDateTime.now());
    }

    public String getMessage(){
        return ("["+timestamp+"] "+sender.getLogin() + ": " + content);
    }
}
