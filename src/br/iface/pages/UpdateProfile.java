package br.iface.pages;

import br.iface.controllers.UpdateProfileController;
import br.iface.entities.User;

import javax.print.DocFlavor;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UpdateProfile {
    private List<User> users;
    private UpdateProfileController updateProfileController;

    public UpdateProfile(List<User> users){
        this.users = users;
        this.updateProfileController = new UpdateProfileController(users);
    }

    public void Menu(User current_login){
        String login, password, name;
        Scanner input = new Scanner(System.in);

        System.out.println("Essas são suas informações cadastradas:");
        System.out.println(current_login);

        while (true){
            try {
                System.out.println("Qual informação você quer modificar?\n1. Login\n2. Senha\n3. Nome\n4. Nenhuma");

                int op = input.nextInt();

                if (op == 1) {
                    System.out.println("Digite o novo login:");
                    login = input.next();
                    System.out.printf("Login escolhido: %s\n", login);

                    updateProfileController.updateLogin(current_login, login);
                } else if (op == 2) {
                    System.out.println("Digite a nova senha:");

                    password = input.next();

                    System.out.printf("Senha escolhida: %s\n", password);
                    updateProfileController.updatePass(current_login, password);
                } else if (op == 3) {
                    System.out.println("Digite o novo nome que será exibido no seu perfil:");

                    input.nextLine();
                    name = input.nextLine();

                    System.out.printf("Nome escolhido: %s\n", name);
                    updateProfileController.updateName(current_login, name);
                } else if(op == 4) {
                    System.out.printf("Alterações feitas com sucesso! Suas novas informações são:\n%s\n\n", current_login);
                    return;
                }
                else{
                    System.out.println("Isso não é uma opção.\n");
                }
            }catch (InputMismatchException e){
                input.next(); //limpa o buffer
                System.out.println("Você precisa inserir um número.\n");
            }
        }
    }


}
