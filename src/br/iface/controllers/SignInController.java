package br.iface.controllers;

import br.iface.entities.User;
import br.iface.entities.relationships.UserData;

import java.util.List;

public class SignInController {
    private List<User> users;

    public SignInController(List<User> users){
        this.users = users;
    }

    public User createsAccount(String login, String password, String name){
        UserData new_user = new UserData(login, password, name);

        this.users.add(new_user);
        return new_user;
    }

    public boolean existingLogin(String login){
        User some_user;
        for(int i = 0; i < this.users.size(); i++){
            some_user = this.users.get(i);
            if(some_user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
}
