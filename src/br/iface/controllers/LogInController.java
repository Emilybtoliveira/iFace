package br.iface.controllers;

import br.iface.core.Dependencies;
import br.iface.core.Input;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;

import java.util.List;

public class LogInController {
    private Dependencies app_dependencies;
    private List<User> users;
    private Input input;

    public LogInController(Dependencies app_dependencies){
        this.app_dependencies = app_dependencies;
        this.users = app_dependencies.getUsers();
        this.input = new Input();
    }
    public boolean executeLogin(){
        while(true){
            System.out.println("Digite 0 para cancelar a operação a qualquer momento.");

            String login = input.getUserInput("Digite seu login:");
            if (input.isQuit(login)){ return false; }

            String pass = input.getUserInput("Digite a sua senha:");
            if (input.isQuit(pass)){ return false; }

            User some_user = this.authentication(login, pass);

            if(some_user.getLogin() != null){
                System.out.println("Login bem sucedido!\n");
                this.app_dependencies.setCurrentUser(some_user);
                return true;
            } else{
                System.out.println("Senha ou login incorreto\n");
            }
        }
    }

    public User authentication(String login, String password){
        User some_user;

        for (int i = 0; i < this.users.size(); i++) {
            some_user = this.users.get(i);
            if (some_user.getLogin().equals(login)) {
                if(some_user.getPass().equals(password)) {
                    return some_user;
                }
                else{
                    break;
                }
            }
        }

        some_user = new UserData();
        return some_user;
    }
}
