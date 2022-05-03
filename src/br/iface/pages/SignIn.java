package br.iface.pages;
import java.util.Scanner;

import br.iface.controllers.SignInController;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;

import java.util.ArrayList;
import java.util.List;

public class SignIn {
    private List<User> users;
    private SignInController signInController;

    public SignIn(List<User> users){
        this.users = users;
        signInController = new SignInController(users);
    }

    public User Menu(){
        String login, password, name;
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("Digite um login:");
            login = input.next();

            if(signInController.existingLogin(login)){
                System.out.println("Esse login já está sendo usado. Escolha outro.");
            } else {
                System.out.printf("Login escolhido: %s\n", login);
                break;
            }
        }

        System.out.println("Digite uma senha:");

        password = input.next();

        System.out.printf("Senha escolhida: %s\n", password);
        input.nextLine();

        System.out.println("Digite o nome que será exibido no seu perfil:");

        name = input.nextLine();

        System.out.printf("Nome escolhido: %s\n", name);

        //fazer uma verificação de que funcionou
        User new_user = signInController.createsAccount(login, password, name);

        System.out.println("Você foi cadastrado!\n");
        return new_user;
    }
}

