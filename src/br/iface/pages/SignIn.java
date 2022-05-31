package br.iface.pages;
import java.util.Scanner;

import br.iface.controllers.SignInController;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;
import br.iface.validations.InputValidations;
import br.iface.validations.InvalidInputException;
import java.util.List;

public class SignIn {
    private final SignInController signInController;

    public SignIn(List<User> users){
        signInController = new SignInController(users);
    }

    public User Menu(){
        User new_user =  new UserData();
        String login, password, name;
        System.out.println("Digite 0 a qualquer momento para cancelar a operação.");

        login = loginInput();
        if (!login.equals("")) {
            password = passwordInput();
            if(!password.equals("")) {
                name = nameInput();
                if(!name.equals("")) {
                    new_user = signInController.createsAccount(login, password, name);
                    System.out.println("Você foi cadastrado!\n");
                }
            }
        }

        return new_user;
    }

    public String loginInput(){
        String login;
        Scanner input = new Scanner(System.in);
        InputValidations validator = new InputValidations();

        while(true) {
            try {
                System.out.println("Digite um login:");
                login = input.nextLine();

                if (isQuit(login)){return "";}

                validator.hasSpecialChar(login);
                validator.hasTheMinimumSize(login, 4);

                if (signInController.existingLogin(login)) {
                    System.out.println("Esse login já está sendo usado. Escolha outro.\n");
                } else {
                    System.out.printf("Login escolhido: %s\n", login);
                    break;
                }
            } catch (InvalidInputException e){
                System.out.println(e.getMessage());
            }
        }
        return login;
    }

    public String passwordInput(){
        String password;
        Scanner input = new Scanner(System.in);
        InputValidations validator = new InputValidations();

        while(true){
            try {
                System.out.println("Digite uma senha:");
                password = input.nextLine();

                if (isQuit(password)){return "";}

                validator.hasSomeSpecialChar(password);
                validator.hasTheMinimumSize(password, 5);

                System.out.printf("Senha escolhida: %s\n", password);
                break;
            } catch (InvalidInputException e){
                System.out.println(e.getMessage());
            }
        }

        return password;
    }

    public String nameInput(){
        String name;
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome que será exibido no seu perfil:");
        name = input.nextLine();

        if (isQuit(name)){return "";}

        System.out.printf("Nome escolhido: %s\n", name);

        return name;
    }

    private boolean isQuit(String input){
        return (input.equals("0")?true:false);
    }

}

