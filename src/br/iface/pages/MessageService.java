package br.iface.pages;

import br.iface.classes.Message;
import br.iface.classes.MessageHistory;
import br.iface.classes.User;

import java.util.List;
import java.util.Scanner;

public class MessageService {
    public MessageService(){}

    public void Menu(User current_user, List users){
        int op, i, aux;

        Scanner input = new Scanner(System.in);
        User some_user = new User();

        System.out.println("Escolha uma opção:\n1. Ver minhas conversas\n2. Iniciar um novo chat\n3.Voltar");
        op = input.nextInt();

        if(op == 1){
            int n_chats = current_user.getChats().size();

            if(n_chats == 0){
                System.out.println("Você não tem conversas ativas ainda.\n");
                return;
            }
            else {
                System.out.println("Você tem conversas ativas com esses contatos:");

                for (i = 0; i < n_chats; i++) {
                    MessageHistory messageHistory = (MessageHistory) current_user.getChats().get(i);

                    String user1 = messageHistory.getUser1().getLogin();
                    String user2 = messageHistory.getUser2().getLogin();

                    if(user1.equals(current_user.getLogin())){ //se user1 == usuario atual, entao exibe o nome do user2
                        some_user = messageHistory.getUser2();
                        System.out.printf("\t%d - %s\n", i+1, user2);
                    } else {  //se user2 == usuario atual, entao exibe o nome do user1
                        some_user = messageHistory.getUser1();
                        System.out.printf("\t%d - %s\n", i+1, user1);
                    }
                }

                System.out.println("Digite o numero do chat que você quer abrir. Digite 0 para cancelar a operação.");
                op = input.nextInt();

                if(op == 0){
                    return;
                }
                else if (op > n_chats){
                    System.out.println("Essa opção não existe.");
                }
                else{
                    MessageHistory messageHistory = (MessageHistory) current_user.getChats().get(op-1);
                    List all_messages = messageHistory.getHistory();

                    for(i = 0; i < all_messages.size(); i++){
                        Message msg = (Message) all_messages.get(i);
                        System.out.println(msg.getMessage());
                    }

                    input.nextLine();
                    System.out.printf("Digite uma mensagem ou 0 para cancelar a operação: ");
                    String content = input.nextLine();

                    if(content.equals("0")){
                        System.out.println();
                        return;
                    }

                    Message new_msg = new Message(current_user, some_user, content);
                    messageHistory.newMessage(new_msg);
                    System.out.println("Mensagem enviada!\n");
                    return;
                }

            }
        }
        else if(op == 2){
            aux = 0;
            System.out.printf("Insira o login do usuário remetente: ");
            String recipient = input.next();

            if(recipient.equals(current_user.getLogin())){
                System.out.println("Você não pode conversar consigo mesmo.\n");
                return;
            }

            for (i = 0; i < users.size(); i++) {
                some_user = (User) users.get(i);
                //System.out.println(some_user.getLogin());

                if (some_user.getLogin().equals(recipient)) {

                    // verifica se ja existe historico para aquele remetente
                    if(existingHistory(current_user, some_user)){
                        System.out.println("Você já possui uma conversa ativa com esse usuário.\n");
                        return;
                    }
                    else{
                        //recebe a mensagem
                        input.nextLine();
                        System.out.printf("Digite a mensagem: ");
                        String content = input.nextLine();

                        //cria o objeto Message
                        Message current_message = new Message(current_user, some_user, content);

                        //cria o histórico de conversa entre os dois usuarios (remetente e destinatario)
                        MessageHistory current_history = new MessageHistory(current_user, some_user);

                        //adiciona o historico de conversa ao usuario remetente
                        current_user.setNewChat(current_history);

                        //adiciona o historico de conversa ao usuario destinatario
                        some_user.setNewChat(current_history);

                        //adiciona a mensagem criada ao histórico do chat
                        current_history.newMessage(current_message);
                    }

                    System.out.println("Mensagem enviada!\n");
                    aux = 1;

                    break;
                }
            }
            if(aux == 0){
                System.out.println("Esse login não foi encontrado.");
            }
        }
        else{
            return;
        }
    }

    private boolean existingHistory(User user1, User user2) {
        int n_chats = user1.getChats().size();

        if (n_chats != 0) {

            for (int i = 0; i < n_chats; i++) {
                MessageHistory messageHistory = (MessageHistory) user1.getChats().get(i);
                /* compara se os usuários envolvidos em cada historico do usuario1(current_user) sao
                    iguais ao usuario2(remetente) */
                System.out.printf("%s %s\n", messageHistory.getUser1().getLogin(), messageHistory.getUser2().getLogin());
                System.out.printf("%s %s\n", user1.getLogin(), user2.getLogin());

                if (messageHistory.getUser1().getLogin().equals(user2.getLogin()) && messageHistory.getUser2().getLogin().equals(user1.getLogin())) {
                    //entao ja existe um historico
                    return true;
                }
                else if(messageHistory.getUser2().getLogin().equals(user1.getLogin()) && messageHistory.getUser1().getLogin().equals(user2.getLogin())){
                    //entao ja existe um historico
                    return true;
                }
            }

        }
        return false;
    }
}