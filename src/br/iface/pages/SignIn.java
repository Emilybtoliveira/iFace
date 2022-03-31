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

    public String Input(){
        String login, password, name;
        Scanner input = new Scanner(System.in);

        System.out.println("Digite um login:");

        login = input.next();

        System.out.printf("Login escolhido: %s\n", login);

        System.out.println("Digite uma senha:");

        password = input.next();

        System.out.printf("Senha escolhida: %s\n", password);
        input.nextLine();

        System.out.println("Digite o nome que será exibido no seu perfil:");

        name = input.nextLine();

        System.out.printf("Nome escolhido: %s\n", name);
        System.out.printf("Você foi cadastrado!\n", name);

        User new_user = new User(login, password, name);
        this.users.add(new_user);

        return login;
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
}
