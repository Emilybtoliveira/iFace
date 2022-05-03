package br.iface.pages;

import br.iface.controllers.MessagingController;
import br.iface.entities.User;

import java.util.List;
import java.util.Scanner;

public class MessagingService {
    private List<User> users;
    private MessagingController messagingController;

    public MessagingService(List<User> users) {
        this.users = users;
        this.messagingController = new MessagingController(users);
    }

    public void Menu(User current_user) {
        int op;
        Scanner input = new Scanner(System.in);

        System.out.println("Escolha uma opção:\n1. Ver minhas conversas\n2. Iniciar um novo chat\n3.Voltar");
        op = input.nextInt();

        if (op == 1) {
            messagingController.showActiveChats(current_user);
        } else if (op == 2) {
            System.out.printf("Insira o login do usuário remetente: ");
            String recipient = input.next();
            messagingController.startAChat(current_user, recipient);
        } else {
            return;
        }
    }
}

