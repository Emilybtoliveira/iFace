package br.iface.pages;

import br.iface.controllers.FeedController;
import br.iface.entities.Feed;
import br.iface.entities.PostMessage;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;
import br.iface.entities.relationships.UserFeed;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FeedService {
    UserFeed mainPublicFeed;
    private FeedController feedController;

    public FeedService(UserFeed mainPublicFeed){
        this.mainPublicFeed = mainPublicFeed;
        this.feedController = new FeedController(mainPublicFeed);
    }

    public void Menu(User current_user) {
        UserData current_user_data = (UserData) current_user;
        PostMessage new_post;
        int op;
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.printf("Escolha uma opção:\n1. Ver feed\n2. Criar novo post\n3. Ver meus posts\nDigite qualquer outro número para cancelar a operação.\n");
                op = input.nextInt();

                if (op == 1) {
                    feedController.printFeed(current_user);
                } else if (op == 2) {
                    input.nextLine();
                    System.out.print("Escreva o conteúdo do post: ");
                    String content = input.nextLine();

                    while(true) {
                        try {
                            System.out.print("1. Post público para a rede\n2. Post privado para os meus amigos\nDigite qualquer número para cancelar a operação: ");
                            op = input.nextInt();

                            switch (op) {
                                case 1:
                                    new_post = new PostMessage(current_user, "public", content);
                                    feedController.newPublicPost(new_post);

                                    //adiciona à lista de posts do usuário
                                    current_user_data.setNewPost(new_post);
                                    break;
                                case 2:
                                    new_post = new PostMessage(current_user, "private", content);
                                    feedController.newPrivatePost(new_post, current_user_data);

                                    //adiciona à lista de posts do usuário
                                    current_user_data.setNewPost(new_post);
                                    break;
                                default:
                                    System.out.println("Operação cancelada.\n");
                                    return;
                            }
                            System.out.println("Seu post foi publicado!\n");
                            return;
                        } catch (InputMismatchException e) {
                            input.next(); //limpa o buffer
                            System.out.println("Você precisa inserir um número.\n");
                        }
                    }
                } else if (op == 3) {
                    feedController.printAllMyPosts(current_user_data);
                } else {
                    System.out.println("Operação cancelada.\n");
                    return;
                }
                return;
            } catch (InputMismatchException e) {
                input.next(); //limpa o buffer
                System.out.println("Você precisa inserir um número.\n");
            }
        }
    }
}
