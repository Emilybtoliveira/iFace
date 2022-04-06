package br.iface.pages;

import br.iface.classes.Community;
import br.iface.classes.Feed;
import br.iface.classes.MessageHistory;
import br.iface.classes.User;

import java.util.List;

public class RemoveAccount {
    public RemoveAccount(){}

    public void RemoveAccountRoutine(User current_user, List users, Feed mainPublicFeed){
        int i;
        User some_user;
        Community some_com;
        Feed some_feed;
        MessageHistory some_msg_hist;


        //remove o usuario da lista de amizades dos usuarios
        for(i = 0; i < current_user.getMyfriends().size(); i++){
            some_user = (User) current_user.getMyfriends().get(i);

            some_user.getMyfriends().remove(current_user);
            some_user.removeFriendRequest(current_user); //se existir

            //excluir posts do feed privado
            some_user.getMyPrivateFeed().removePostsFromUser(current_user);

        }

        //excluir historico de mensagens
        for(i = 0; i < current_user.getChats().size(); i++){
            some_msg_hist = (MessageHistory) current_user.getChats().get(i);

            if(some_msg_hist.getUser1().equals(current_user) || some_msg_hist.getUser2().equals(current_user)){
                //remove o historico
                some_msg_hist.getUser2().removeChat(some_msg_hist);
                some_msg_hist.getUser1().removeChat(some_msg_hist);
            }
        }

        //excluir da lista de membros das comunidades e os posts do feed da comunidade
        for(i = 0; i < current_user.getMyCommunities().size(); i++){
            some_com = (Community) current_user.getMyCommunities().get(i);

            //remove da lista de membros
            some_com.removeMember(current_user);
            //remove os posts do feed
            some_feed = (Feed) some_com.getFeed();
            some_feed.removePostsFromUser(current_user);

            //excluir as comunidades criadas pelo usuario da lista dos membros
            if(some_com.getAdm().equals(current_user)){
                for(int j = 0; j < some_com.getMembers().size(); j++){
                    User member = (User) some_com.getMembers().get(j);
                    member.removeFromCommunity(some_com);
                }
            }
        }

        //excluir posts do feed publico
        mainPublicFeed.removePostsFromUser(current_user);

        //remove o usuario da lista de usuarios
        users.remove(current_user);

        //remove as informações do usuario
        current_user.deleteAllInfo();

        System.out.println("Seus dados e sua conta foram excluídos. Se mudar de ideia, cadastre-se novamente!:)\n");
    }
}
