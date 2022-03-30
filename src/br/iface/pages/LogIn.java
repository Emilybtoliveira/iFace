package br.iface.pages;

import java.util.List;
import java.util.Scanner;

public class LogIn {
    private List users;

    public LogIn(List users){
        this.users = users;
    }

    public void Input(){
        String login, password;

        System.out.println("Digite seu login:");

        Scanner input = new Scanner(System.in);
        login = input.next();

        System.out.printf("Login escolhido: %s\n", login);
    }
}
