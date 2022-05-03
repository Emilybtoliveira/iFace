package br.iface.controllers;

import br.iface.entities.Feed;
import br.iface.entities.PostMessage;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;
import br.iface.entities.relationships.UserFeed;

import java.util.List;

public class FeedController {
    UserFeed mainPublicFeed = new UserFeed();

    public FeedController(){}

    public FeedController(UserFeed mainPublicFeed){
        this.mainPublicFeed = mainPublicFeed;
    }

    public void newPublicPost(PostMessage new_post){
        this.mainPublicFeed.addNewMessage(new_post);
    }

    public void newPrivatePost(PostMessage new_post, UserData current_user){
        UserData some_user;
        UserFeed some_feed;

        //adicionar ao feed do proprio usuario
        some_feed = current_user.getMyPrivateFeed();
        some_feed.addNewMessage(new_post);

        //adicionar ao feed de cada amigo do usuario
        List<User> my_friends = current_user.getMyfriends();

        for(int i = 0; i < my_friends.size(); i++){
            some_user = (UserData) my_friends.get(i);

            some_feed = some_user.getMyPrivateFeed();
            some_feed.addNewMessage(new_post);
        }
    }

    public void printFeed(User current_user){
        int i, aux = 0;
        PostMessage some_post;
        UserData current_user_data = (UserData) current_user;

        System.out.printf("\nFeed de noticias\n");
        System.out.println("-----------------------------------\n");

        //printa os posts privados (seus e dos amigos) primeiro
        List<PostMessage> private_feed_posts = current_user_data.getMyPrivateFeed().getMessages();

        for(i = 0; i < private_feed_posts.size(); i++){
            some_post = private_feed_posts.get(i);

            System.out.println(some_post);
            aux += 1;
        }

        List<PostMessage> public_feed_posts = this.mainPublicFeed.getMessages();
        //printa os posts publicos da rede
        for(i = 0; i < public_feed_posts.size(); i++){
            some_post = public_feed_posts.get(i);

            System.out.println(some_post);
            aux += 1;
        }

        if(aux == 0){
            System.out.println("Nenhum post foi publicado ainda.");
        }

        System.out.println();
    }

    public void printAllMyPosts(UserData current_user) {
        PostMessage current_post;
        List<PostMessage> posts = current_user.getMyPosts();
        int n_posts = posts.size();

        if(n_posts == 0){
            System.out.println("Você não fez publicações ainda.");
        }
        else {
            for (int i = 0; i < n_posts; i++) {
                current_post = posts.get(i);
                System.out.println(current_post);
            }
        }

        System.out.println();
        return;
    }

    public void moveFeedOver(UserData destiny_user, UserData source_user){
        List<PostMessage> source_user_posts = source_user.getMyPosts();
        PostMessage current_post;
        UserFeed current_user_feed = destiny_user.getMyPrivateFeed();

        for(int i = 0; i < source_user_posts.size(); i++){
            current_post = source_user_posts.get(i);

            if(current_post.getPostPrivacy().equals("private")){
                current_user_feed.addNewMessage(current_post);
            }
        }
    }
}
