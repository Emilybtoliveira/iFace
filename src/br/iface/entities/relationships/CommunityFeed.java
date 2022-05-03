package br.iface.entities.relationships;

import br.iface.entities.*;

import java.util.ArrayList;
import java.util.List;

public class CommunityFeed extends Community implements Feed<PostMessage>{
    private List<PostMessage> postMessages;

    public CommunityFeed(User adm, String name, String description){
        super(adm, name, description);
        this.postMessages = new ArrayList();
    }


    public void addNewMessage(PostMessage new_post){
        this.postMessages.add(0, new_post); //na timeline, os mais recentes aparecem primeiro
    };


    public List<PostMessage> getMessages(){
        return this.postMessages;
    }


    public void removeMessagesFromUser(User user){
        PostMessage some_post;

        for(int i = 0; i < this.postMessages.size(); i++){
            some_post = this.postMessages.get(i);
            //System.out.println("excluindo...");

            if(some_post.getSender().equals(user)){
                // System.out.println(some_post.getFormattedPost());

                this.postMessages.remove(some_post);
            }
        }
    }

}
