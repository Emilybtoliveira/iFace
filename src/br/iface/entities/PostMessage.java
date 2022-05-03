package br.iface.entities;

import br.iface.entities.relationships.CommunityFeed;

public class PostMessage extends Message {
    private String privacy; //public ou private

    //se for um post para o feed
    public PostMessage(User sender, String privacy, String content){
        super(sender, null, content);
        this.privacy = privacy;
    }

    //se for um post para comunidade
    public PostMessage(User sender, String content, CommunityFeed dest_community){
        super(sender, dest_community, content);
        this.privacy = "community";
    }

    @Override
    public String toString(){
        return this.sender.getName()+"\t\t"+this.timestamp+"\n"+"@"+this.sender.getLogin()+"\t"+this.privacy+" post\n"+"\t"+this.content+"\n";
    }

    /*Esse metodo é unico para post*/
    public String getPostPrivacy(){
        return this.privacy;
    }

    /*Esse metodo é unico para post*/
    public User getSender() {
        return this.sender;
    }
}
