package br.iface.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Community {
    private User adm; //usuario criador
    private String name;
    private String description;
    private List<User> members;

    public Community(User adm, String name, String description){
        this.adm = adm;
        this.name = name;
        this.description = description;
        this.members = new ArrayList();

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

    public List<User> getMembers(){
        return this.members;
    }

    public void addMember(User new_member){
        this.members.add(new_member);
    }

    public void removeMember(User member){
        this.members.remove(member);
    }

}
