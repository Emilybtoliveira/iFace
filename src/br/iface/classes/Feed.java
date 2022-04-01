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
}
