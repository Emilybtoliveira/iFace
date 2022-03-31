import br.iface.pages.SignIn;
import br.iface.classes.User;
import br.iface.pages.UpdateProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int op;
        List users = new ArrayList();
        String current_user = "";
        System.out.println("Bem vindo ao iFace!");
        Scanner input = new Scanner(System.in);
        User some_user = new User();


        while(true) {
            if (current_user != "") {
                System.out.printf("Você está navegando como %s.\n", current_user);
            }

            System.out.println("Escolha uma opção:\n1. Cadastrar e logar como outro usuário\n2. Alterar informações do usuário atual\n3. Adicionar um amigo\n4. Sair do programa");
            op = input.nextInt();

            if (op == 1) {
                //SignIn
                SignIn signInPage = new SignIn();
                current_user = signInPage.Input();
                //signInPage.printAllUsers();

                users = signInPage.getUsers();

            }
            else if (op == 2){
                UpdateProfile updateProfilePage = new UpdateProfile();
                current_user = updateProfilePage.Input(current_user, users);
            }
            else if(op == 3){
                System.out.printf("Escolha uma opção:\n 1. Ver minhas solicitações\n2. Enviar nova solicitação de amizade.\n3. Cancelar");
                op = input.nextInt();

                //Ver minhas solicitações
                if(op == 1){
                    System.out.println("Suas solicitações pendentes:");
                }
                //Solicitar nova amizade
                else if(op == 2) {
                    System.out.println("Insira o login do amigo que quer adicionar:");
                    String friend = input.next();

                    for (int i = 0; i < users.size(); i++) {
                        some_user = (User) users.get(i);

                        if (some_user.getLogin() == friend) {
                            //System.out.printf("Login:%s Senha:%s Nome:%s\n", some_user.getLogin(), some_user.getPass(), some_user.getName());
                            some_user.setFriends_requests(current_user);
                            System.out.printf("Solicitação de amizade enviada para o usuário %s\n", some_user.getName());
                            break;
                        }
                    }
                }
            }
            else{
                return;
            }
        }
    }
}
