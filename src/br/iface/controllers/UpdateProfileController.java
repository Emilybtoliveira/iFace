package br.iface.controllers;

import br.iface.entities.User;

import java.util.List;

public class UpdateProfileController {
    private List<User> users;

    public UpdateProfileController(List<User> users){
        this.users = users;
    }

    public boolean updateLogin(User current_user, String new_login){
        current_user.setLogin(new_login);
        return true;
    }

    public boolean updatePass(User current_user, String new_pass){
        current_user.setPass(new_pass);
        return true;
    }

    public boolean updateName(User current_user, String new_name){
        current_user.setName(new_name);
        return true;
    }
}
