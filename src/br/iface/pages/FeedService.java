package br.iface.pages;

import br.iface.classes.Feed;
import br.iface.classes.Post;
import br.iface.classes.User;

import java.util.List;
import java.util.Scanner;

public class FeedService {
    public FeedService(){}

    public void Menu(User current_user, Feed mainPublicFeed){
        Post new_post;
        int op;
        Scanner input = new Scanner(System.in);

        System.out.printf("Escolha uma opção:\n1. Ver feed\n2. Criar novo post\n3. Ver meus posts\nDigite 0 para cancelar a operação.\n");
        op = input.nextInt();

        if(op == 1){
            printFeed(current_user, mainPublicFeed);
        } else if (op == 2) {
            input.nextLine();
            System.out.print("Escreva o conteúdo do post: ");
            String content = input.nextLine();

            System.out.print("1. Post público para a rede\n2. Post privado para os meus amigos\nDigite 0 para cancelar a operação: ");
            op = input.nextInt();

            switch (op){
                case 1:
                    new_post = new Post(current_user, "public", content);
                    newPublicPost(new_post, mainPublicFeed);

                    //adiciona à lista de posts do usuário
                    current_user.setNewPost(new_post);
                    break;
                case 2:
                    new_post = new Post(current_user, "private", content);
                    newPrivatePost(new_post, current_user);

                    //adiciona à lista de posts do usuário
                    current_user.setNewPost(new_post);
                    break;
                default:
                    System.out.println("Operação cancelada.\n");
                    return;
            }
            System.out.println("Seu post foi publicado!\n");
        } else if(op == 3){
            printAllMyPosts(current_user);
        }
        else{
            System.out.println("Operação cancelada.\n");
            return;
        }
    }

    private void newPublicPost(Post new_post, Feed mainPublicFeed){
        mainPublicFeed.newPost(new_post);
    }

    private void newPrivatePost(Post new_post, User current_user){
        int i;
        User some_user;
        Feed some_feed;

        //adicionar ao feed do proprio usuario
        some_feed = current_user.getMyPrivateFeed();
        some_feed.newPost(new_post);

        //adicionar ao feed de cada amigo do usuario
        List my_friends = current_user.getMyfriends();

        for(i = 0; i < my_friends.size(); i++){
            some_user = (User) my_friends.get(i);

            some_feed = some_user.getMyPrivateFeed();
            some_feed.newPost(new_post);
        }
    }

    private void printFeed(User current_user, Feed mainPublicFeed){
        int i, aux = 0;
        Post some_post;

        System.out.printf("\nFeed de noticias\n");
        System.out.println("-----------------------------------\n");

        //printa os posts privados (seus e dos amigos) primeiro
        List private_feed_posts = current_user.getMyPrivateFeed().getPosts();

        for(i = 0; i < private_feed_posts.size(); i++){
            some_post = (Post) private_feed_posts.get(i);

            System.out.println(some_post.getFormattedPost());
            aux += 1;
        }

        List public_feed_posts = mainPublicFeed.getPosts();
        //printa os posts publicos da rede
        for(i = 0; i < public_feed_posts.size(); i++){
            some_post = (Post) public_feed_posts.get(i);

            System.out.println(some_post.getFormattedPost());
            aux += 1;
        }

        if(aux == 0){
            System.out.println("Nenhum post foi publicado ainda.");
        }

        System.out.println();
    }

    private void printAllMyPosts(User current_user) {
        Post current_post;
        List posts = current_user.getMyPosts();
        int n_posts = posts.size();

        if(n_posts == 0){
            System.out.println("Você não fez publicações ainda.");
        }
        else {
            for (int i = 0; i < n_posts; i++) {
                current_post = (Post) posts.get(i);
                System.out.println(current_post.getFormattedPost());
            }
        }

        System.out.println();
        return;
    }

    public void moveFeedOver(User current_user, User source_user){
        List source_user_posts = source_user.getMyPosts();
        Post current_post;
        Feed current_user_feed = current_user.getMyPrivateFeed();

        for(int i = 0; i < source_user_posts.size(); i++){
            current_post = (Post) source_user_posts.get(i);

            if(current_post.getPostPrivacy().equals("private")){
                current_user_feed.newPost(current_post);
            }
        }
    }
}
