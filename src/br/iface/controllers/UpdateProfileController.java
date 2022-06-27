package br.iface.controllers;

import br.iface.core.Dependencies;
import br.iface.core.Input;
import br.iface.entities.User;

import java.util.List;

public class UpdateProfileController {
    private List<User> users;
    private Dependencies app_dependencies;
    private Input input;

    public UpdateProfileController(Dependencies app_dependencies){
        this.app_dependencies  = app_dependencies;
        this.users = app_dependencies.getUsers();
        this.input = new Input();

    }

    public boolean executeUpdate(String attribute) {
        if (attribute.equals("login")) {

            String login = input.getUserLogin(new SignInController(app_dependencies));
            this.updateLogin(app_dependencies.getCurrentUser(), login);

        } else if (attribute.equals("pass")){

            String password = input.getUserPass();
            this.updatePass(app_dependencies.getCurrentUser(), password);

        } else if (attribute.equals("name")){

            String name = input.getUserName();
            this.updateName(app_dependencies.getCurrentUser(), name);
        }

        return true;
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
