package br.iface.pages;

import br.iface.classes.User;

import java.util.List;
import java.util.Scanner;

public class LogIn {

    public LogIn(){}

    public User Input(List users){
        String login, password;
        Scanner input = new Scanner(System.in);
        User some_user =  new User();

        while(true){
            System.out.println("Digite 1 para cancelar a operação a qualquer momento.");

            System.out.println("Digite seu login:");
            login = input.next();

            if (isQuit(login)){return some_user;}
            System.out.printf("Login digitado: %s\n", login);

            System.out.println("Digite a senha:");
            password = input.next();
            if (isQuit(password)){return some_user;}

            System.out.printf("Senha digitada: %s\n", password);

            for (int i = 0; i < users.size(); i++) {
                some_user = (User) users.get(i);
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
            some_user =  new User();
            System.out.println("Senha ou login incorreto\n");
        }
    }

    private boolean isQuit(String input){
        return (input.equals("1")?true:false);
    }
}
