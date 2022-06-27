package br.iface.core.menus;

import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Home;
import br.iface.core.modules.Module;
import br.iface.entities.relationships.UserData;

import java.util.Map;
import java.util.Scanner;

public class SwitchAccountMenu extends Menu {
    private Menu next_menu;

    public SwitchAccountMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.next_menu = new HomeMenu(app_dependencies);
    }

    @Override
    public void Menu(){
        Scanner input = new Scanner(System.in);

        System.out.println("Deseja sair do perfil atual?S/N");
        String bool = input.next();

        if(bool.equals("s") || bool.equals("S")){
            this.setNextMenu();
            app_dependencies.setCurrentUser(new UserData());
            System.out.println("Você foi deslogado.\n");
        } else if(bool.equals("n") || bool.equals("N")){
            System.out.println("Operação cancelada.\n");
        } else{
            System.out.println("Opção inexistente. Abortado.\n");
        }
    }


    public void setNextMenu(){
        this.next_menu = new UnloggedMenu(app_dependencies);
    }

    @Override
    public Menu getNextMenu(){
        return this.next_menu;
    }
}
