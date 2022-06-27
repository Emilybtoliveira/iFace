package br.iface.core;

import br.iface.controllers.SignInController;
import br.iface.validations.InputValidations;
import br.iface.validations.InvalidInputException;

import java.util.Map;
import java.util.Scanner;

public class Input{
    protected Scanner input;

    public Input(){
        this.input = new Scanner(System.in);
    }

    public String getUserInput(String display_input_request){
        String user_input;

        System.out.println(display_input_request);
        user_input = this.input.nextLine();

        return user_input;
    }

    public boolean isUserInputValid(String user_input, String validator_type){
        InputValidations validator = new InputValidations();

        try {
            validator.hasSpecialChar(user_input);

            if(validator_type.equals("login")) {
                validator.hasTheMinimumSize(user_input, 4);
            } else if(validator_type.equals("pass")){
                validator.hasTheMinimumSize(user_input, 5);
            }

            return true;

        } catch(InvalidInputException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getUserLogin(SignInController signInController){
        while(true) {
            String login = getUserInput("Digite um login: ");

            if (isQuit(login)){ return ""; }

            if (isUserInputValid(login, "login")) {
                if (!signInController.existingLogin(login)) {
                    System.out.printf("Login escolhido: %s\n", login);

                    return login;
                } else {
                    System.out.println("Esse login já está sendo usado. Escolha outro.\n");
                }
            }
        }
    }

    public String getUserPass(){
        while(true) {
            String password = getUserInput("Digite uma senha: ");
            if (isQuit(password)){ return ""; }

            if (isUserInputValid(password, "pass")) {
                System.out.printf("Senha escolhida: %s\n", password);

                return password;
            }
        }
    }

    public String getUserName(){
        String name = getUserInput("Digite o nome que será exibido no seu perfil: ");
        if (isQuit(name)){ return ""; }

        System.out.printf("Nome escolhido: %s\n", name);

        return name;
    }

    public boolean isQuit(String input){
        return (input.equals("0")?true:false);
    }
}
