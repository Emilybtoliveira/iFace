package br.iface.pages;

import br.iface.entities.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    public MainMenu(){}

    public String Menu(User current_user){
        int op;
        Scanner input = new Scanner(System.in);
        boolean flag = false;

        while(!flag) {
            try {
                if (current_user.getName() == null) {

                    System.out.println("Escolha uma opcao:\n1. Fazer login\n2. Cadastrar e logar como novo usuário \n3. Sair do programa");

                    op = input.nextInt();

                    switch (op) {
                        case 1:
                            return "login";
                        case 2:
                            return "signin";
                        case 3:
                            return "quit";
                    }
                } else {
                    System.out.printf("Você está navegando como %s.\n", current_user.getLogin());
                    System.out.println("Escolha uma opção:\n1. Alterar informações do usuário atual\n" +
                            "2. Gerenciar amizades\n3. Chats\n4. Comunidades\n5. Feed\n6. Excluir minha conta\n7. Trocar de conta" +
                            "\n8. Sair do programa");

                    op = input.nextInt();

                    switch (op) {
                        case 1:
                            return "update";
                        case 2:
                            return "friendsmanage";
                        case 3:
                            return "chats";
                        case 4:
                            return "community";
                        case 5:
                            return "feed";
                        case 6:
                            return "delete";
                        case 7:
                            return "switchacc";
                        case 8:
                            return "quit";
                        default:
                            return "";
                    }
                }

                flag = true;
            } catch (InputMismatchException e) {
                input.next(); //limpa o buffer
                System.out.println("Você precisa inserir um número.\n");
            }
        }

        return "";
    }

}
