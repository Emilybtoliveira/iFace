package br.iface.pages;

import br.iface.classes.Community;
import br.iface.classes.Post;
import br.iface.classes.User;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CommunityMenu {

    public CommunityMenu(){}

    public void Menu(User current_user, List users){
        int op;
        Scanner input = new Scanner(System.in);

        System.out.println("Escolha uma opção:\n1. Ver minhas comunidades\n2. Criar nova comunidade");
        op = input.nextInt();

        if(op == 1){
            int n_coms = showCommunities(current_user);

            if(n_coms == 0){
                return;
            }

            System.out.println("Digite o numero da comunidade que deseja ver. Digite 0 para cancelar operação.");
            op = input.nextInt();

            if (op > 0 && op <= n_coms){
                optionsOnCommunity(current_user, op - 1, users);
            } else {
                System.out.println("Essa opção é inválida.");
            }
        }
        else if(op == 2) {
            input.nextLine();

            System.out.println("Escolha um nome para a comunidade (digite 0 para cancelar a operação): ");
            String name = input.nextLine();

            if (!name.equals("0")) {
                //input.nextLine();
                System.out.println("Escreva uma breve descrição da comunidade: ");
                String description = input.nextLine();

                Community new_com = new Community(current_user, name, description);
                current_user.setNewCommunity(new_com);

                System.out.println("Comunidade criada!\nProcure-a no seu menu de comunidades para adicionar membros e fazer posts ;)\n");
                return;
            }

            System.out.println("Operação cancelada\n");
        }
    }

    private int showCommunities(User current_user){
        int i;

        List communities = current_user.getMyCommunities();
        int n_coms = communities.size();

        if(n_coms == 0){
            System.out.println("Você ainda não possui comunidades.\n");
        }
        else {
            System.out.println("Essas são suas comunidades.");

            for (i = 0; i < n_coms; i++){
                Community some_com = (Community) communities.get(i);
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

    private void optionsOnCommunity(User current_user, int com_index, List users){
        int op;
        String aux;
        Scanner input = new Scanner(System.in);

        List communities = current_user.getMyCommunities();
        Community chosen_com = (Community) communities.get(com_index);

        System.out.printf("\nComunidade %s\n", chosen_com.getName().toUpperCase(Locale.ROOT));
        System.out.println("-----------------------------------");

        if(chosen_com.getAdm().getLogin().equals(current_user.getLogin())){
            System.out.println("Escolha uma operação para realizar na comunidade:\n1. Adicionar um membro\n2. Fazer um post" +
                    "\n3. Ver o feed\nDigite 0 para cancelar a operação.");

            op = input.nextInt();
            if(op == 1){
                System.out.print("Digite o login do usuário que quer adicionar: ");
                aux = input.next();

                addAMember(aux, chosen_com, users);
            }
            else if(op == 2) {
                createNewPost(current_user, chosen_com);
            }
            else if(op == 3) {
                showFeed(chosen_com);
            }
        } else {
            System.out.println("Escolha uma operação para realizar na comunidade:\n1. Fazer um post\n2. Ver o feed\nDigite 0 para cancelar a operação.");

            op = input.nextInt();
            if(op == 1) {
                createNewPost(current_user, chosen_com);
            } else if(op == 2){
                showFeed(chosen_com);
            }
        }
    }

    private void addAMember(String newMember, Community chosen_com, List users){
        int i;
        User some_user;

        for (i = 0; i < users.size(); i++) {
            some_user = (User) users.get(i);
            //System.out.println(some_user.getLogin());
            if (some_user.getLogin().equals(newMember)) {
                chosen_com.addMember(some_user);
                some_user.setNewCommunity(chosen_com);

                System.out.printf("@%s foi adicionado à comunidade!\n\n", some_user.getLogin());
                return;
            }
        }

        System.out.println("Esse login não foi encontrado.\n");
    }

    private void createNewPost(User current_user, Community chosen_com){
        String content;
        Scanner input = new Scanner(System.in);

        System.out.print("Escreva o conteúdo do post (digite 0 para cancelar operação): ");
        content = input.nextLine();

        if(!content.equals("0")) {
            Post new_post = new Post(current_user, content, chosen_com);

            current_user.setNewPost(new_post);
            chosen_com.addPostToFeed(new_post);

            System.out.println("Seu post foi publicado!\n");
            return;
        }

        System.out.println("Operação cancelada.\n");
    }

    private void showFeed(Community chosen_com){
        Post some_post;
        List feed = chosen_com.getFeedPosts();
        int n_posts = feed.size();

        if(n_posts == 0){
            System.out.println("Nenhuma publicação foi feita nessa comunidade.\n");
        }
        else {
            System.out.printf("\n%s\nFeed\n", chosen_com.getName().toUpperCase(Locale.ROOT));
            System.out.println("-----------------------------------\n");

            for (int i = 0; i < n_posts; i++){
                some_post = (Post) feed.get(i);

                System.out.printf("%s\n", some_post.getFormattedPost());
            }
        }
    }
}