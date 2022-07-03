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
    private String last_chosen_option;
    public LoginMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.loginController = new LogInController(app_dependencies);
        this.last_chosen_option = "unlogged";
    }

    @Override
    public void Menu(){
        if(this.loginController.executeLogin()){
            this.last_chosen_option = "home";
        }
        this.setNextMenu();
    }

    private void setNextMenu(){
        if (this.last_chosen_option.equals("home")){
            this.next_menu = new HomeMenu(app_dependencies);
        } else{
            this.next_menu = new UnloggedMenu(app_dependencies);
        }
    }

    @Override
    public Menu getNextMenu() {
        return this.next_menu;
    }

}
