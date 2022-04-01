package br.iface.pages;
import java.util.Scanner;
import br.iface.classes.User;
import java.util.ArrayList;
import java.util.List;

public class SignIn {
    private List users;

    public SignIn(){
        this.users = new ArrayList();
    }

    public User Input(){
        String login, password, name;
        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("Digite um login:");
            login = input.next();

            if(existingLogin(login)){
                System.out.println("Esse login já está sendo usado. Escolha outro.");
            } else {
                System.out.printf("Login escolhido: %s\n", login);
                break;
            }
        }

        System.out.println("Digite uma senha:");

        password = input.next();

        System.out.printf("Senha escolhida: %s\n", password);
        input.nextLine();

        System.out.println("Digite o nome que será exibido no seu perfil:");

        name = input.nextLine();

        System.out.printf("Nome escolhido: %s\n", name);
        System.out.printf("Você foi cadastrado!\n\n", name);

        User new_user = new User(login, password, name);
        this.users.add(new_user);

        return new_user;
    }

    public void printAllUsers(){
        for (int i = 0; i < this.users.size(); i++){
            User user = (User) this.users.get(i);
            System.out.printf("%s %s %s\n", user.getLogin(), user.getPass(), user.getName());
        }
    }

    public List getUsers(){
        return this.users;
    }

    private boolean existingLogin(String login){
        User some_user;
        for(int i = 0; i < this.users.size(); i++){
            some_user = (User) this.users.get(i);
            if(some_user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
}
