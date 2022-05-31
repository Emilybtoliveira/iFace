package br.iface.pages;

import br.iface.controllers.UpdateProfileController;
import br.iface.entities.User;

import javax.print.DocFlavor;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UpdateProfile {
    private List<User> users;
    private final UpdateProfileController updateProfileController;

    public UpdateProfile(List<User> users){
        this.users = users;
        this.updateProfileController = new UpdateProfileController(users);
    }

    public void Menu(User current_login){
        String login, password, name;
        Scanner input = new Scanner(System.in);
        SignIn signInProcedure = new SignIn(this.users);

        System.out.println("\nEssas são suas informações cadastradas:");
        System.out.println(current_login);

        while (true){
            try {
                System.out.println("Qual informação você quer modificar?\n1. Login\n2. Senha\n3. Nome\n4. Nenhuma");

                int op = input.nextInt();

                if(op > 0 && op < 5) {
                    System.out.println("\nDigite 0 a qualquer momento para cancelar a operação.");

                    if (op == 1) {
                        login = signInProcedure.loginInput();
                        if (!login.equals("")) {
                            updateProfileController.updateLogin(current_login, login);
                        }
                    } else if (op == 2) {
                        password = signInProcedure.passwordInput();
                        if (!password.equals("")) {
                            updateProfileController.updatePass(current_login, password);
                        }
                    } else if (op == 3) {
                        name = signInProcedure.nameInput();
                        if (!name.equals("")) {
                            updateProfileController.updateName(current_login, name);
                        }
                    } else {
                        System.out.printf("Alterações feitas com sucesso! Suas novas informações são:\n%s\n\n", current_login);
                        return;
                    }
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
