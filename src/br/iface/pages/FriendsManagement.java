package br.iface.pages;

import br.iface.classes.User;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class FriendsManagement {

    public FriendsManagement(){}

    public void Menu(User current_user, List users){
        User some_user;
        int op, i, aux, n_pending;
        Scanner input = new Scanner(System.in);

        System.out.printf("Escolha uma opção:\n1. Ver minhas solicitações recebidas\n2. Enviar nova solicitação de amizade.\n3. Ver minhas amizades\n4. Cancelar\n");
        op = input.nextInt();

        //Ver minhas solicitações
        if(op == 1){
            showFriendsRequests(current_user);
        }
        //Solicitar nova amizade
        else if(op == 2) {
            newFriendRequest(current_user, users);
        }
        //Ver amizades
        else if(op == 3){
            showMyFriends(current_user);
        }

        System.out.println();
        return;
    }

    private void showFriendsRequests(User current_user){
        User some_user;
        int op, i, n_pending;
        Scanner input = new Scanner(System.in);

        if(current_user.getFriendsRequests().size() == 0){ //se nao existir
            System.out.println("Você não tem solicitações pendentes.\n");
            return;
        } else { //se existir
            System.out.println("Suas solicitações pendentes:");
            n_pending = current_user.getFriendsRequests().size();
            for (i = 0; i < n_pending; i++) {
                some_user = (User) current_user.getFriendsRequests().get(i);

                System.out.printf("\t%d. %s (%s)\n", i + 1, some_user.getName(), some_user.getLogin());
            }
        }
        while(true){
            System.out.println("Digite o número da solicitação que você deseja aceitar. Digite 0 para cancelar a operação.");
            op = input.nextInt();

            if(op == 0){
                return;
            }
            else if (op > n_pending){
                System.out.println("Essa opção não existe.");
            }
            else{
                some_user = (User) current_user.getFriendsRequests().get(op-1);
                //remove pending
                current_user.removeFriendRequest(some_user);
                //adiciona o usuario à lista de friends de current_user
                current_user.setMyFriends(some_user);
                //adiciona current_user à lista de friends de some_user
                some_user.setMyFriends(current_user);
                //decrementa o n_pending
                n_pending--;
                //puxa as mensagens privadas do novo amigo para o feed do usuario e o contrario tbm
                FeedService feedServicePage = new FeedService();
                feedServicePage.moveFeedOver(current_user, some_user);
                feedServicePage.moveFeedOver(some_user, current_user);

                if (n_pending == 0){
                    System.out.println("Todas as suas solicitações pendentes foram aceitas. Você pode ver seus amigos selecionando no menu.");
                    break;
                }
            }
        }
    }

    private void newFriendRequest(User current_user, List users){
        User some_user;
        int i;
        Scanner input = new Scanner(System.in);

        System.out.println("Insira o login do amigo que quer adicionar:");
        String friend = input.next();

        //Nao pode solicitar a si mesmo
        if(friend.equals(current_user)){
            System.out.println("Você não pode solicitar amizade a si mesmo.");
        }
        else if(isAlreadyAFriend(current_user, friend)){
            System.out.printf("Você já é amigo de %s.\n", friend);
        }
        else{
            for (i = 0; i < users.size(); i++) {
                some_user = (User) users.get(i);
                //System.out.println(some_user.getLogin());
                if (some_user.getLogin().equals(friend)) {
                    //System.out.printf("Login:%s Senha:%s Nome:%s\n", some_user.getLogin(), some_user.getPass(), some_user.getName());
                    if(hasAlreadyInvited(current_user, some_user)){
                        System.out.printf("Você já solicitou amizade à %s. Aguarde que el@ aceite.\n", some_user.getLogin());
                        return;
                    }
                    else{
                        some_user.setAFriendsRequest(current_user);
                        System.out.printf("Solicitação de amizade enviada para o usuário %s. Quando ele aceitar, aparecerá na sua lista de amigos!\n", some_user.getName());
                        return;
                    }
                }
            }

            System.out.println("Esse login não foi encontrado.");
        }
    }

    private void showMyFriends(User current_user){
        User some_user;
        int i;

        int n_friends =  current_user.getMyfriends().size();
        if(n_friends == 0){
            System.out.println("Você ainda não tem amigos. Selecione a opção de enviar nova solicitação de amizade no menu!\n");
        }
        else {
            System.out.println("Essa é sua lista de amigos:");
            for (i = 0; i < n_friends; i++) {
                some_user = (User) current_user.getMyfriends().get(i);
                System.out.printf("%d. %s (%s)\n", i + 1, some_user.getName(), some_user.getLogin());
            }
        }

        //System.out.println();
        return;
    }

    private boolean hasAlreadyInvited(User current_user, User lookup_friend){
        for(int i = 0; i < lookup_friend.getFriendsRequests().size(); i++){
            User some_user = (User) lookup_friend.getFriendsRequests().get(i);
            if(some_user.getLogin().equals(current_user.getLogin())){
                return true;
            }
        }
        return false;
    }

    private boolean isAlreadyAFriend(User current_user, String lookup_friend){
        for(int i = 0; i < current_user.getMyfriends().size(); i++){
            User some_user = (User) current_user.getMyfriends().get(i);
            if(some_user.getLogin().equals(lookup_friend)){
                return true;
            }
        }
        return false;
    }

}
