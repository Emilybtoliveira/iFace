package br.iface.controllers;

import br.iface.entities.User;
import br.iface.entities.relationships.UserData;

import java.util.List;

public class LogInController {
    private List<User> users;
    public LogInController(List<User> users){
        this.users = users;
    }

    public User authentication(String login, String password){
        User some_user;

        for (int i = 0; i < this.users.size(); i++) {
            some_user = this.users.get(i);
            if (some_user.getLogin().equals(login)) {
                if(some_user.getPass().equals(password)) {
                    System.out.println("Login bem sucedido!\n");
                    return some_user;
                }
                else{
                    break;
                }
            }
        }

        some_user =  new UserData();
        System.out.println("Senha ou login incorreto\n");
        return some_user;
    }
}
