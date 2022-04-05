package br.iface.classes;

import java.util.ArrayList;
import java.util.List;

public class Community {
    private User adm; //usuario criador
    private String name;
    private String description;
    private List members;
    private Feed feed;

    public Community(User adm, String name, String description){
        this.adm = adm;
        this.name = name;
        this.description = description;
        this.members = new ArrayList();
        this.feed = new Feed();

        this.members.add(adm);
    }

    public User getAdm(){
        return this.adm;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public List getMembers(){
        return this.members;
    }

    public void addMember(User new_member){
        this.members.add(new_member);
    }

    public void removeMember(User member){
        this.members.remove(member);
    }

    public void addPostToFeed(Post new_post){
        this.feed.newPost(new_post);
    }

    public List getFeedPosts(){
        return this.feed.getPosts();
    }

    public Feed getFeed(){
        return this.feed;
    }
}
