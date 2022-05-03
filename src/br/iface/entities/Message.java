package br.iface.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Message {
    protected User sender;
    protected Object recipient;
    protected String content;
    protected String timestamp;

    public Message(User sender, Object recipient, String content){
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.timestamp = dtf.format(LocalDateTime.now());
    }

}
