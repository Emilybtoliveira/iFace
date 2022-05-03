package br.iface.pages;

import br.iface.controllers.LogInController;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;

import java.util.List;
import java.util.Scanner;

public class LogIn {
    private List<User> users;
    private LogInController logInController;

    public LogIn(List<User> users){
        this.users = users;
        this.logInController = new LogInController(users);
    }

    public User Menu(){
        String login, password;
        Scanner input = new Scanner(System.in);
        User some_user = new UserData();

        while(true){
            System.out.println("Digite 0 para cancelar a operação a qualquer momento.");

            System.out.println("Digite seu login:");
            login = input.next();

            if (isQuit(login)){ return some_user;}
            System.out.printf("Login digitado: %s\n", login);

            System.out.println("Digite a senha:");
            password = input.next();
            if (isQuit(password)){return some_user;}

            System.out.printf("Senha digitada: %s\n", password);

            return this.logInController.authentication(login, password);
        }
    }

    private boolean isQuit(String input){
        return (input.equals("0")?true:false);
    }
}
