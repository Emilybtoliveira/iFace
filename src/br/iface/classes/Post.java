package br.iface.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private User owner;
    private String privacy; //public ou private
    private String content;
    private Community dest_community; //comunidade onde o post sera feito (se houver)
    private String timestamp;

    //se for um post para o feed
    public Post(User owner, String privacy, String content){
        this.owner = owner;
        this.privacy = privacy;
        this.content = content;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.timestamp = dtf.format(LocalDateTime.now());
    }

    //se for um post para comunidade
    public Post(User owner, String content, Community dest_community){
        this.owner = owner;
        this.privacy = "community";
        this.content = content;
        this.dest_community = dest_community;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MMMM HH:mm");
        this.timestamp = dtf.format(LocalDateTime.now());
    }

    public String getFormattedPost(){
        return this.owner.getName()+"\t\t"+this.timestamp+"\n"+"@"+this.owner.getLogin()+"\t"+this.privacy+" post\n"+"\t"+this.content+"\n";
    }

    public String getPostPrivacy(){
        return this.privacy;
    }

    public User getOwner() {
        return owner;
    }
}
