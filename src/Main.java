import br.iface.pages.*;
import br.iface.entities.*;
import br.iface.entities.relationships.UserData;
import br.iface.entities.relationships.UserFeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //variáveis de controle
        String bool, op;
        Scanner input = new Scanner(System.in);

        //variáveis de objetos
        List<User> users = new ArrayList();
        User current_user = new UserData();
        UserFeed mainPublicFeed = new UserFeed();

        SignIn signInPage = new SignIn(users);
        UpdateProfile updateProfilePage = new UpdateProfile(users);
        MainMenu mainMenuPage = new MainMenu();
        LogIn logInPage = new LogIn(users);
        MessagingService messagingServicePage = new MessagingService(users);
        CommunityManagement communityManagementPage = new CommunityManagement(users);
        FeedService feedServicePage = new FeedService(mainPublicFeed);
        FriendsManagement friendsManagementPage = new FriendsManagement(users);

        System.out.println("Bem vindo ao iFace!");

        while(true) {
            op = mainMenuPage.Menu(current_user);
            //SignIn
            if (op.equals("signin")) {

                current_user = signInPage.Menu();
                //users.add(current_user);
                //signInPage.printAllUsers();
            }
            else if(op.equals("login")){
                current_user = logInPage.Menu();
            }
            else if (op.equals("update")){
                updateProfilePage.Menu(current_user);
            }
            else if(op.equals("friendsmanage")){
                friendsManagementPage.Menu(current_user);
            }
            else if (op.equals("switchacc")){
                System.out.println("Deseja sair do perfil atual?S/N");
                bool = input.next();

                if(bool.equals("s") || bool.equals("S")){
                    current_user = new UserData();
                    System.out.println("Você foi deslogado.\n");
                }
            }
            else if(op.equals("chats")){
                messagingServicePage.Menu(current_user);
            }
            else if(op.equals("community")){
                communityManagementPage.Menu(current_user);
            }
            else if(op.equals("delete")){
                System.out.printf("Tem certeza de que deseja excluir sua conta? Todas as suas informações, mensagens e posts serão apagados. (S/N) ");
                bool = input.next();

                if(bool.equals("s") || bool.equals("S")){
                    RemoveAccount removeAccount = new RemoveAccount();
                    removeAccount.RemoveAccountRoutine(current_user, users, mainPublicFeed);
                    current_user = new UserData();
                }         
            }
            else if(op.equals("feed")){
                feedServicePage.Menu(current_user);
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
