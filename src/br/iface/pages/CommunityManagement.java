package br.iface.pages;

import br.iface.controllers.CommunityController;
import br.iface.entities.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommunityManagement {
    private List<User> users;
    private CommunityController communityController;

    public CommunityManagement(List<User> users){
        this.users = users;
        this.communityController = new CommunityController(users);
    }

    public void Menu(User current_user){
        int op;
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Escolha uma opção:\n1. Ver minhas comunidades\n2. Criar nova comunidade");
                op = input.nextInt();

                if (op == 1) {
                    int n_coms = communityController.showCommunities(current_user);

                    if (n_coms == 0) {
                        return;
                    }

                    System.out.println("Digite o numero da comunidade que deseja ver. Digite qualquer outro número para cancelar a operação.");
                    op = input.nextInt();

                    if (op > 0 && op <= n_coms) {
                        communityController.optionsOnCommunity(current_user, op - 1);
                    } else {
                        System.out.println("Operação cancelada.");
                    }

                    return;
                } else if (op == 2) {
                    input.nextLine();

                    System.out.println("Escolha um nome para a comunidade (digite 0 para cancelar a operação)");
                    String name = input.nextLine();

                    if (!name.equals("0")) {
                        //input.nextLine();
                        System.out.println("Escreva uma breve descrição da comunidade: ");
                        String description = input.nextLine();

                        communityController.createACommunity(current_user, name, description);
                    } else {
                        System.out.println("Operação cancelada\n");
                    }

                    return;
                } else{
                    System.out.println("Essa opção não existe.\n");
                }
            }catch (InputMismatchException e){
                input.next(); //limpa o buffer
                System.out.println("Você precisa inserir um número.\n");
            }
        }
    }


}