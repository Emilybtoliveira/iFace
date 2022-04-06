package br.iface.classes;

import java.util.ArrayList;
import java.util.List;

public class Feed {
    private List posts;

    public Feed(){
        this.posts = new ArrayList();
    }

    public void newPost(Post post){
        this.posts.add(0, post); //na timeline, os mais recentes aparecem primeiro
    }

    public List getPosts(){
        return this.posts;
    }

    public void removePostsFromUser(User user){
        Post some_post;

        for(int i = 0; i < this.posts.size(); i++){
            some_post = (Post) this.posts.get(i);
            //System.out.println("excluindo...");

            if(some_post.getOwner().equals(user)){
               // System.out.println(some_post.getFormattedPost());

                this.posts.remove(some_post);
            }
        }
    }
}
