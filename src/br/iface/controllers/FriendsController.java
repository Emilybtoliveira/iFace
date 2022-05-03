package br.iface.controllers;

import br.iface.entities.User;
import br.iface.entities.relationships.UserData;
import br.iface.pages.FeedService;

import java.util.List;
import java.util.Scanner;

public class FriendsController {
    private List<User> users;

    public FriendsController(List<User> users){
        this.users = users;
    }

    public void manageFriendsRequests(User current_user){
        User some_user = new UserData();
        UserData current_user_data, some_user_data;
        int op, n_pending;
        Scanner input = new Scanner(System.in);

        current_user_data = (UserData) current_user;
        some_user_data = (UserData) some_user;

        if(current_user_data.getFriendsRequests().size() == 0){ //se nao existir
            System.out.println("Você não tem solicitações pendentes.\n");
            return;
        } else { //se existir
            System.out.println("Suas solicitações pendentes:");
            n_pending = current_user_data.getFriendsRequests().size();
            for (int i = 0; i < n_pending; i++) {
                some_user = current_user_data.getFriendsRequests().get(i);

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
                //pega o usuario escolhido
                some_user = current_user_data.getFriendsRequests().get(op-1);
                //remove pending
                current_user_data.removeFriendRequest(some_user);
                //adiciona o usuario à lista de friends de current_user_data
                current_user_data.setNewFriend(some_user);
                //adiciona current_user_data à lista de friends de some_user
                some_user_data.setNewFriend(current_user);
                //decrementa o n_pending
                n_pending--;
                //puxa as mensagens privadas do novo amigo para o feed do usuario e o contrario tbm
                FeedController feedController = new FeedController();
                feedController.moveFeedOver(current_user_data, some_user_data);
                feedController.moveFeedOver(some_user_data, current_user_data);

                if (n_pending == 0){
                    System.out.println("Todas as suas solicitações pendentes foram aceitas. Você pode ver seus amigos selecionando no menu.");
                    break;
                }
            }
        }
    }

    public void newFriendRequest(User current_user, String friends_login){
        User some_user;
        UserData some_user_data;

        //Nao pode solicitar a si mesmo
        if(friends_login.equals(current_user.getLogin())){
            System.out.println("Você não pode solicitar amizade a si mesmo.");
        }
        else if(isAlreadyAFriend(current_user, friends_login)){
            System.out.printf("Você já é amigo de %s.\n", friends_login);
        }
        else{
            for (int i = 0; i < this.users.size(); i++) {
                some_user = this.users.get(i);
                if (some_user.getLogin().equals(friends_login)) {
                    //System.out.printf("Login:%s Senha:%s Nome:%s\n", some_user.getLogin(), some_user.getPass(), some_user.getName());
                    some_user_data = (UserData) some_user;

                    if(hasAlreadyInvited(current_user, some_user_data)){
                        System.out.printf("Você já solicitou amizade à %s. Aguarde que el@ aceite.\n", some_user.getLogin());
                        return;
                    }
                    else{
                        some_user_data.setAFriendsRequest(current_user);
                        System.out.printf("Solicitação de amizade enviada para o usuário %s. Quando ele aceitar, aparecerá na sua lista de amigos!\n", some_user.getName());
                        return;
                    }
                }
            }

            System.out.println("Esse login não foi encontrado.");
        }
    }

    public void showMyFriends(User current_user){
        User some_user;
        UserData current_user_data;
        current_user_data = (UserData) current_user;

        int n_friends =  current_user_data.getMyfriends().size();
        if(n_friends == 0){
            System.out.println("Você ainda não tem amigos. Selecione a opção de enviar nova solicitação de amizade no menu!\n");
        }
        else {
            System.out.println("Essa é sua lista de amigos:");
            for (int i = 0; i < n_friends; i++) {
                some_user = current_user_data.getMyfriends().get(i);
                System.out.printf("%d. %s (%s)\n", i + 1, some_user.getName(), some_user.getLogin());
            }
        }
        return;
    }

    public boolean hasAlreadyInvited(User current_user, UserData lookup_friend){
        for(int i = 0; i < lookup_friend.getFriendsRequests().size(); i++){
            User some_user = lookup_friend.getFriendsRequests().get(i);
            if(some_user.getLogin().equals(current_user.getLogin())){
                return true;
            }
        }
        return false;
    }

    public boolean isAlreadyAFriend(User current_user, String lookup_friend){
        UserData current_user_data = (UserData) current_user;
        for(int i = 0; i < current_user_data.getMyfriends().size(); i++){
            User some_user = current_user_data.getMyfriends().get(i);
            if(some_user.getLogin().equals(lookup_friend)){
                return true;
            }
        }
        return false;
    }
}
