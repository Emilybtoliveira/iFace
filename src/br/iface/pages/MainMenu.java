package br.iface.pages;

import br.iface.classes.User;

import java.util.Scanner;

public class MainMenu {
    public MainMenu(){}

    public String Menu(User current_user){
        int op;
        Scanner input = new Scanner(System.in);

        if (current_user.getName() == null) {

            System.out.println("Escolha uma opção:\n1. Fazer login\n2. Cadastrar e logar como novo usuário \n3. Sair do programa");

            op = input.nextInt();

            switch (op){
                case 1:
                    return "login";
                case 2:
                    return "signin";
                case 3:
                    return "quit";
            }
        }
        else{
            System.out.printf("Você está navegando como %s.\n", current_user.getLogin());
            System.out.println("Escolha uma opção:\n1. Alterar informações do usuário atual\n" +
                    "2. Gerenciar amizades\n3. Chats\n4. Trocar de conta\n5. Sair do programa");

            op = input.nextInt();

            switch (op){
                case 1:
                    return "update";
                case 2:
                    return "friendsmanage";
                case 3:
                    return "chats";
                case 4:
                    return "switchacc";
                case 5:
                    return "quit";

            }
        }

        return "";
    }
}
