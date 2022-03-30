package br.iface.pages;

import br.iface.classes.User;

import java.util.List;
import java.util.Scanner;

public class UpdateProfile {
    public UpdateProfile(){}

    public String Input(String current_login, List users){
        String password, name;
        Scanner input = new Scanner(System.in);
        User some_user = new User();

        if(users.size() == 0){
            System.out.println("Não existem usuários cadastrados ainda :(");
            return "";
        }

        System.out.println("Essas são suas informações cadastradas:");

        for (int i = 0; i < users.size(); i++){
            some_user = (User) users.get(i);

            if(some_user.getLogin() == current_login){
                System.out.printf("Login:%s Senha:%s Nome:%s\n", some_user.getLogin(), some_user.getPass(), some_user.getName());

                password = some_user.getPass();
                name = some_user.getName();
                break;
            }
        }


        while (true){
            System.out.println("Qual informação você quer modificar?\n1. Login\n2. Senha\n3. Nome\n4.Nenhuma");

            int op = input.nextInt();

            if (op == 1){
                System.out.println("Digite o novo login:");
                current_login = input.next();
                System.out.printf("Login escolhido: %s\n", current_login);

                some_user.setLogin(current_login);
            }
            else if (op == 2){
                System.out.println("Digite a nova senha:");

                password = input.next();

                System.out.printf("Senha escolhida: %s\n", password);
                some_user.setPass(password);
            }
            else if(op == 3){
                System.out.println("Digite o novo nome que será exibido no seu perfil:");
                input.nextLine();

                name = input.nextLine();

                System.out.printf("Nome escolhido: %s\n", name);
                some_user.setName(name);
            }
            else{
                System.out.printf("Alterações feitas com sucesso! Suas novas informações são:\nLogin:%s Senha:%s Nome:%s\n\n", some_user.getLogin(), some_user.getPass(), some_user.getName());
                return current_login;
            }
        }
    }


}
