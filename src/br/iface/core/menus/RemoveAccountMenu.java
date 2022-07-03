package br.iface.core.menus;

import br.iface.controllers.CommunityController;
import br.iface.controllers.RemoveAccountController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Home;
import br.iface.entities.relationships.UserData;

import java.util.Scanner;

public class RemoveAccountMenu extends Menu {
    private Menu next_menu;
    private RemoveAccountController removeAccountController;
    private String last_chosen_option;

    public RemoveAccountMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.removeAccountController = new RemoveAccountController(app_dependencies.getUsers(), app_dependencies.getMainPublicFeed());
        this.last_chosen_option = "cancel";
    }

    @Override
    public void Menu(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Tem certeza de que deseja excluir sua conta? Todas as suas informações, mensagens e posts serão apagados. (S/N) ");
        String bool = input.next();

        if(bool.equals("s") || bool.equals("S")){
            removeAccountController.RemoveAccountRoutine(app_dependencies.getCurrentUser());
            app_dependencies.setCurrentUser(new UserData());
            last_chosen_option = "remove";
        } else if(bool.equals("n") || bool.equals("N")){
            System.out.println("Operação cancelada.\n");
        } else{
            System.out.println("Opção inexistente. Abortado.\n");
        }

        this.setNextMenu();
    }

    private void setNextMenu(){
        if (last_chosen_option.equals("remove")) {
            this.next_menu = new UnloggedMenu(app_dependencies);
        } else{
            this.next_menu = new HomeMenu(app_dependencies);
        }
    }

    @Override
    public Menu getNextMenu() {
        return this.next_menu;
    }
}