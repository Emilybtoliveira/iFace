package br.iface.pages;

import br.iface.controllers.FriendsController;
import br.iface.entities.User;

import java.util.List;
import java.util.Scanner;

public class FriendsManagement {
    private List<User> users;
    private FriendsController friendsController;

    public FriendsManagement(List<User> users){
        this.users = users;
        friendsController = new FriendsController(users);
    }

    public void Menu(User current_user){
        int op;
        Scanner input = new Scanner(System.in);

        System.out.printf("Escolha uma opção:\n1. Ver minhas solicitações recebidas\n" +
                "2. Enviar nova solicitação de amizade.\n3. Ver minhas amizades\n4. Cancelar\n");
        op = input.nextInt();

        //Ver minhas solicitações
        if(op == 1){
            friendsController.manageFriendsRequests(current_user);
        }
        //Solicitar nova amizade
        else if(op == 2) {
            System.out.println("Insira o login do amigo que quer adicionar:");
            String friend = input.next();

            friendsController.newFriendRequest(current_user, friend);
        }
        //Ver amizades
        else if(op == 3){
            friendsController.showMyFriends(current_user);
        }

        System.out.println();
        return;
    }
}
