package br.iface.controllers;

import br.iface.entities.Community;
import br.iface.entities.PostMessage;
import br.iface.entities.User;
import br.iface.entities.relationships.CommunityFeed;
import br.iface.entities.relationships.UserData;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CommunityController {
    private List<User> users;
    Scanner input = new Scanner(System.in);

    public CommunityController(List<User> users) {
        this.users = users;
    }

    public boolean createACommunity(User owner){
        UserData owner_data = (UserData) owner;

       // input.nextLine();
        System.out.println("Escolha um nome para a comunidade (digite 0 para cancelar a operação)");
        String name = input.nextLine();

        if (!name.equals("0")) {
            System.out.println("Escreva uma breve descrição da comunidade: ");
            String description = input.nextLine();
            CommunityFeed new_com = new CommunityFeed(owner, name, description);
            owner_data.setNewCommunity(new_com);

            System.out.println("Comunidade criada!\nProcure-a no seu menu de comunidades para adicionar membros e fazer posts ;)\n");
        }

        return true;
    }

    public void selectACommunity(User current_user){
        int n_coms = showCommunities(current_user);

        if (n_coms == 0) {
            return;
        }

        System.out.println("Digite o numero da comunidade que deseja ver. Digite qualquer outro número para cancelar a operação.");
        int op = input.nextInt();

        if (op > 0 && op <= n_coms) {
            optionsOnCommunity(current_user,op - 1);
        } else {
            System.out.println("Operação cancelada.");
        }

        return;
    }

    public int showCommunities(User current_user){
        UserData current_user_data = (UserData) current_user;

        List<CommunityFeed> communities = current_user_data.getCommunities();
        int n_coms = communities.size();

        if(n_coms == 0){
            System.out.println("Você ainda não possui comunidades.\n");
        }
        else {
            System.out.println("Essas são as suas comunidades:");

            for (int i = 0; i < n_coms; i++){
                Community some_com = communities.get(i);
                if(current_user.getLogin().equals(some_com.getAdm().getLogin())){
                    System.out.printf("\t%d. %s (Adm)\n\t\t%s\n\n", i+1, some_com.getName(), some_com.getDescription());
                }
                else{
                    System.out.printf("\t%d. %s\n\t\t%s\n\n", i+1, some_com.getName(), some_com.getDescription());
                }
            }
        }

        return n_coms;
    }

    public void optionsOnCommunity(User current_user, int com_index){
        int op;
        String aux;
        UserData current_user_data = (UserData) current_user;

        List<CommunityFeed> communities = current_user_data.getCommunities();
        CommunityFeed chosen_com = communities.get(com_index);

        System.out.printf("\nComunidade %s\n", chosen_com.getName().toUpperCase(Locale.ROOT));
        System.out.println("-----------------------------------");

        while (true) {
            try {
                if (chosen_com.getAdm().getLogin().equals(current_user.getLogin())) {
                    System.out.println("Escolha uma operação para realizar na comunidade:\n1. Adicionar um membro\n2. Fazer um post" +
                            "\n3. Ver o feed\nDigite qualquer número para cancelar a operação.");

                    op = input.nextInt();
                    if (op == 1) {
                        System.out.print("Digite o login do usuário que quer adicionar: ");
                        aux = input.next();

                        addAMember(aux, chosen_com);
                    } else if (op == 2) {
                        createNewPost(current_user_data, chosen_com);
                    } else if (op == 3) {
                        showFeed(chosen_com);
                    }
                    else{
                        System.out.println("Operação cancelada.\n");
                    }
                } else {
                    System.out.println("Escolha uma operação para realizar na comunidade:\n1. Fazer um post\n2. Ver o feed" +
                            "\nDigite qualquer número para cancelar a operação.");

                    op = input.nextInt();
                    if (op == 1) {
                        createNewPost(current_user_data, chosen_com);
                    } else if (op == 2) {
                        showFeed(chosen_com);
                    }
                }
                return;
            } catch (InputMismatchException e){
                input.next(); //limpa o buffer
                System.out.println("Você precisa inserir um número.\n");
            }
        }
    }

    private void addAMember(String newMember, CommunityFeed chosen_com){
        int i;
        User some_user;

        for (i = 0; i < this.users.size(); i++) {
            some_user = this.users.get(i);
            //System.out.println(some_user.getLogin());
            if (some_user.getLogin().equals(newMember)) {
                UserData some_user_data = (UserData) some_user;
                chosen_com.addMember(some_user);
                some_user_data.setNewCommunity(chosen_com);

                System.out.printf("@%s foi adicionado à comunidade!\n\n", some_user.getLogin());
                return;
            }
        }

        System.out.println("Esse login não foi encontrado.\n");
    }

    private void createNewPost(UserData current_user, CommunityFeed chosen_com){
        String content;

        System.out.print("Escreva o conteúdo do post (digite 0 para cancelar operação): ");

        input.nextLine();
        content = input.nextLine();
        System.out.println(content);

        if(!content.equals("0")) {
            PostMessage new_post = new PostMessage(current_user, content, chosen_com);

            current_user.setNewPost(new_post);
            chosen_com.addNewMessage(new_post);

            System.out.println("Seu post foi publicado!\n");
            return;
        }

        System.out.println("Operação cancelada.\n");
    }

    private void showFeed(CommunityFeed chosen_com){
        PostMessage some_post;
        List<PostMessage> feed = chosen_com.getMessages();
        int n_posts = feed.size();

        if(n_posts == 0){
            System.out.println("Nenhuma publicação foi feita nessa comunidade.\n");
        }
        else {
            System.out.printf("\n%s\nFeed\n", chosen_com.getName().toUpperCase(Locale.ROOT));
            System.out.println("-----------------------------------\n");

            for (int i = 0; i < n_posts; i++){
                some_post = feed.get(i);

                System.out.printf("%s\n", some_post);
            }
        }
    }
}
