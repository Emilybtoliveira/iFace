import br.iface.classes.Feed;
import br.iface.pages.*;
import br.iface.classes.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //variáveis de controle
        String bool, op;
        Scanner input = new Scanner(System.in);

        //variáveis de objetos
        List users = new ArrayList();
        User current_user = new User();
        SignIn signInPage = new SignIn();
        UpdateProfile updateProfilePage = new UpdateProfile();
        MainMenu mainMenuPage = new MainMenu();
        LogIn logInPage = new LogIn();
        MessageService messageServicePage = new MessageService();
        CommunityMenu communityMenuPage = new CommunityMenu();
        Feed mainPublicFeed = new Feed();
        FeedService feedServicePage = new FeedService();

        System.out.println("Bem vindo ao iFace!");

        while(true) {
            op = mainMenuPage.Menu(current_user);
            //SignIn
            if (op.equals("signin")) {

                current_user = signInPage.Input();

                users = signInPage.getUsers();
                //signInPage.printAllUsers();
            }
            else if(op.equals("login")){
                current_user = logInPage.Input(users);
            }
            else if (op.equals("update")){
                current_user = updateProfilePage.Input(current_user, users);
            }
            else if(op.equals("friendsmanage")){
                FriendsManagement friendsManagementPage = new FriendsManagement();
                friendsManagementPage.Menu(current_user, users);
            }
            else if (op.equals("switchacc")){
                System.out.println("Deseja sair do perfil atual?S/N");
                bool = input.next();

                if(bool.equals("s") || bool.equals("S")){
                    current_user = new User();
                    System.out.println("Você foi deslogado.\n");
                }
            }
            else if(op.equals("chats")){
                messageServicePage.Menu(current_user, users);
            }
            else if(op.equals("community")){
                communityMenuPage.Menu(current_user, users);
            }
            else if(op.equals("feed")){
                feedServicePage.Menu(current_user, mainPublicFeed);
            }
            else if(op.equals("quit")){
                return;
            }
            else{
                System.out.println("Isso não é uma opção.\n");
            }
        }
    }
}
