package br.iface.controllers;

import br.iface.core.Dependencies;
import br.iface.core.Input;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;

import java.util.List;

public class SignInController {
    private Dependencies app_dependencies;
    private List<User> users;
    private Input input;

    public SignInController(Dependencies app_dependencies){
        this.app_dependencies = app_dependencies;
        this.users = app_dependencies.getUsers();
        this.input = new Input();
    }

    public boolean executeSignIn(){
        User new_user;
        String login, password, name;
        System.out.println("Digite 0 a qualquer momento para cancelar a operação.");

        login = input.getUserLogin(new SignInController(app_dependencies));
        if(!login.equals("")) {
            password = input.getUserPass();
            if (!password.equals("")) {
                name = input.getUserName();
                if (!name.equals("")) {
                    new_user = this.createsAccount(login, password, name);
                    this.app_dependencies.setCurrentUser(new_user);

                    System.out.println("Você foi cadastrado!\n");
                    return true;
                }
            }
        }
        return false;
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
