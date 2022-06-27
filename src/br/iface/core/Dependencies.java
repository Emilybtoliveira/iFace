package br.iface.core;

import br.iface.entities.User;
import br.iface.entities.relationships.UserData;
import br.iface.entities.relationships.UserFeed;
import java.util.ArrayList;
import java.util.List;

public class Dependencies {
    private List<User> users;
    private User current_user;
    private UserFeed mainPublicFeed;


    public Dependencies(){
        this.users = new ArrayList();
        this.current_user = new UserData();
        this.mainPublicFeed = new UserFeed();
    }

    public List<User> getUsers() {
        return this.users;
    }

    public User getCurrentUser() {
        return current_user;
    }

    public void setCurrentUser(User current_user){
        this.current_user = current_user;
    }

    public UserFeed getMainPublicFeed() {
        return mainPublicFeed;
    }
}
