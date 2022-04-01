package br.iface.pages;

import br.iface.classes.User;

import java.util.List;
import java.util.Scanner;

public class UpdateProfile {
    public UpdateProfile(){}

    public User Input(User current_login, List users){
        String login, password, name;
        Scanner input = new Scanner(System.in);
        //User some_user = new User();

        if(users.size() == 0){
            System.out.println("Não existem usuários cadastrados ainda :(");
            return current_login;
        } else if (current_login.getLogin() == null){
            System.out.println("Você não está logado.");
            return current_login;
        }

        System.out.println("Essas são suas informações cadastradas:");
        System.out.printf("Login:%s Senha:%s Nome:%s\n", current_login.getLogin(), current_login.getPass(), current_login.getName());

        while (true){
            System.out.println("Qual informação você quer modificar?\n1. Login\n2. Senha\n3. Nome\n4.Nenhuma");

            int op = input.nextInt();

            if (op == 1){
                System.out.println("Digite o novo login:");
                login = input.next();
                System.out.printf("Login escolhido: %s\n", login);

                current_login.setLogin(login);
            }
            else if (op == 2){
                System.out.println("Digite a nova senha:");

                password = input.next();

                System.out.printf("Senha escolhida: %s\n", password);
                current_login.setPass(password);
            }
            else if(op == 3){
                System.out.println("Digite o novo nome que será exibido no seu perfil:");

                input.nextLine();
                name = input.nextLine();

                System.out.printf("Nome escolhido: %s\n", name);
                current_login.setName(name);
            }
            else{
                System.out.printf("Alterações feitas com sucesso! Suas novas informações são:\nLogin:%s Senha:%s Nome:%s\n\n", current_login.getLogin(), current_login.getPass(), current_login.getName());
                return current_login;
            }
        }
    }


}
