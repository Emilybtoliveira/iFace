package br.iface.controllers;

import br.iface.entities.User;
import br.iface.entities.relationships.CommunityFeed;
import br.iface.entities.relationships.MessageFeed;
import br.iface.entities.relationships.UserData;
import br.iface.entities.relationships.UserFeed;

import java.util.List;

public class RemoveAccountController {
    private List<User> users;
    private UserFeed mainPublicFeed;

    public RemoveAccountController(List<User> users, UserFeed mainPublicFeed){
        this.users = users;
        this.mainPublicFeed = mainPublicFeed;
    }

    public void RemoveAccountRoutine(User current_user){
        int i;
        User some_user;
        CommunityFeed some_com;
        MessageFeed some_msg_feed;
        UserData current_user_data = (UserData) current_user;

        //remove o usuario da lista de amizades dos usuarios
        for(i = 0; i < current_user_data.getMyfriends().size(); i++){
            some_user = current_user_data.getMyfriends().get(i);
            UserData some_user_data = (UserData) some_user;

            some_user_data.removeAFriend(current_user);

            //excluir posts do feed privado
            some_user_data.getMyPrivateFeed().removeMessagesFromUser(current_user);
        }

        //excluir historico de mensagens
        for(i = 0; i < current_user_data.getChats().size(); i++){
            some_msg_feed = current_user_data.getChats().get(i);

            if(some_msg_feed.getUser1().equals(current_user) || some_msg_feed.getUser2().equals(current_user)){
                //remove o historico
                UserData user2 = (UserData) some_msg_feed.getUser2();
                UserData user1 = (UserData)some_msg_feed.getUser1();

                user2.removeChat(some_msg_feed);
                user1.removeChat(some_msg_feed);
            }
        }

        //excluir da lista de membros das comunidades e os posts do feed da comunidade
        for(i = 0; i < current_user_data.getCommunities().size(); i++){
            some_com = current_user_data.getCommunities().get(i);

            //remove da lista de membros
            some_com.removeMember(current_user);
            //remove os posts do feed
            some_com.removeMessagesFromUser(current_user);

            //excluir as comunidades criadas pelo usuario da lista dos membros
            if(some_com.getAdm().equals(current_user)){
                for(int j = 0; j < some_com.getMembers().size(); j++){
                    UserData member = (UserData) some_com.getMembers().get(j);
                    member.removeFromCommunity(some_com);
                }
            }
        }

        //excluir posts do feed publico
        this.mainPublicFeed.removeMessagesFromUser(current_user);

        //remove o usuario da lista de usuarios
        this.users.remove(current_user);

        //remove as informações do usuario
        current_user_data.deleteAllInfo();

        System.out.println("Seus dados e sua conta foram excluídos. Se mudar de ideia, cadastre-se novamente!\n");
    }

}
