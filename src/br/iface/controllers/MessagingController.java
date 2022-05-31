package br.iface.controllers;

import br.iface.entities.TextMessage;
import br.iface.entities.User;
import br.iface.entities.relationships.MessageFeed;
import br.iface.entities.relationships.UserData;

import java.util.List;
import java.util.Scanner;

public class MessagingController {
    private List<User> users;

    public MessagingController(List<User> users){
        this.users = users;
    }

    public void showActiveChats(User current_user){
        UserData current_user_data = (UserData) current_user;

        int n_chats = current_user_data.getChats().size();

        if(n_chats == 0){
            System.out.println("Você não tem conversas ativas ainda.\n");
            return;
        }
        else {
            System.out.println("Você tem conversas ativas com esses contatos:");

            for (int i = 0; i < n_chats; i++) {
                MessageFeed messageFeed = current_user_data.getChats().get(i);

                String user1 = messageFeed.getUser1().getLogin();
                String user2 = messageFeed.getUser2().getLogin();

                if(user1.equals(current_user.getLogin())){ //se user1 == usuario atual, entao exibe o nome do user2
                    System.out.printf("\t%d - %s\n", i+1, user2);
                } else {  //se user2 == usuario atual, entao exibe o nome do user1
                    System.out.printf("\t%d - %s\n", i+1, user1);
                }
            }

            chooseAChat(n_chats, current_user_data);
        }
    }

    private void chooseAChat(int n_chats, UserData current_user){
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o numero do chat que você quer abrir. Digite 0 para cancelar a operação.");
        int op = input.nextInt();

        if(op == 0){
            return;
        }
        else if (op > n_chats){
            System.out.println("Essa opção não existe.");
        }
        else{
            MessageFeed messageFeed = current_user.getChats().get(op-1);
            List<TextMessage> all_messages = messageFeed.getMessages();

            for(int i = 0; i < all_messages.size(); i++){
                TextMessage msg = all_messages.get(i);
                System.out.println(msg);
            }

            input.nextLine();
            System.out.printf("Digite uma mensagem ou 0 para cancelar a operação: ");
            String content = input.nextLine();

            if(content.equals("0")){
                System.out.println();
                return;
            }
            else{
                TextMessage new_text_msg;

                if(messageFeed.getUser1().getLogin().equals(current_user.getLogin())){
                    new_text_msg = new TextMessage(current_user, messageFeed.getUser2(), content);
                }
                else{
                    new_text_msg = new TextMessage(current_user, messageFeed.getUser1(), content);
                }

                messageFeed.addNewMessage(new_text_msg);
                System.out.println("Mensagem enviada!\n");
                return;
            }
        }
    }

    public void startAChat(User current_user, String recipient){
        User some_user;
        UserData current_user_data = (UserData) current_user;
        Scanner input = new Scanner(System.in);

        if(recipient.equals(current_user.getLogin())){
            System.out.println("Você não pode conversar consigo mesmo.\n");
            return;
        }
        else {
            for (int i = 0; i < this.users.size(); i++) {
                some_user = this.users.get(i);
                //System.out.println(some_user.getLogin());

                if (some_user.getLogin().equals(recipient)) {
                    // verifica se ja existe historico para aquele remetente
                    if (existingHistory(current_user, some_user)) {
                        System.out.println("Você já possui uma conversa ativa com esse usuário.\n");
                        return;
                    } else {
                        UserData some_user_data = (UserData) some_user;

                        //recebe a mensagem
                        System.out.printf("Digite a mensagem: ");
                        String content = input.nextLine();

                        //cria o objeto Message
                        TextMessage new_text_msg = new TextMessage(current_user, some_user, content);

                        //cria o histórico de conversa entre os dois usuarios (remetente e destinatario)
                        MessageFeed current_history = new MessageFeed(current_user, some_user);

                        //adiciona o historico de conversa ao usuario remetente
                        current_user_data.setNewChat(current_history);

                        //adiciona o historico de conversa ao usuario destinatario
                        some_user_data.setNewChat(current_history);

                        //adiciona a mensagem criada ao histórico do chat
                        current_history.addNewMessage(new_text_msg);
                    }

                    System.out.println("Mensagem enviada!\n");
                    return;
                }
            }
        }

        System.out.println("Esse login não foi encontrado.");
        return;
    }

    private boolean existingHistory(User user1, User user2) {
        UserData user1_data = (UserData) user1;
        int n_chats = user1_data.getChats().size();

        if (n_chats != 0) {

            for (int i = 0; i < n_chats; i++) {
                MessageFeed messageFeed = user1_data.getChats().get(i);
                /* compara se os usuários envolvidos em cada historico do usuario1(current_user) sao
                    iguais ao usuario2(remetente) */

                if (messageFeed.getUser1().getLogin().equals(user2.getLogin()) && messageFeed.getUser2().getLogin().equals(user1.getLogin())) {
                    //entao ja existe um historico
                    return true;
                }
                else if(messageFeed.getUser2().getLogin().equals(user1.getLogin()) && messageFeed.getUser1().getLogin().equals(user2.getLogin())){
                    //entao ja existe um historico
                    return true;
                }
            }

        }
        return false;
    }

}
