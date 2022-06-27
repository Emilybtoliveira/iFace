package br.iface.core.menus;

import br.iface.controllers.LogInController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.entities.User;
import br.iface.entities.relationships.UserData;

import java.util.Scanner;

public class LoginMenu extends Menu{
    private Menu next_menu;
    private LogInController loginController;

    public LoginMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.next_menu = new UnloggedMenu(app_dependencies);
        this.loginController = new LogInController(app_dependencies);
    }

    @Override
    public void Menu(){
        if(this.loginController.executeLogin()){
            this.setNextMenu();
        }
    }

    private void setNextMenu(){
        this.next_menu = new HomeMenu(app_dependencies);
    }

    @Override
    public Menu getNextMenu() {
        return this.next_menu;
    }

}
