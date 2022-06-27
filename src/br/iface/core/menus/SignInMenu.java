package br.iface.core.menus;

import br.iface.controllers.SignInController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;

public class SignInMenu extends Menu {
    private final SignInController signInController;
    private Menu next_menu;
    private String last_chosen_option;

    public SignInMenu(Dependencies app_dependencies){
        super(app_dependencies);
        this.signInController = new SignInController(app_dependencies);
        this.last_chosen_option = "";
        this.next_menu = new UnloggedMenu(app_dependencies);
    }

    public void Menu(){
        if(this.signInController.executeSignIn()){
            this.last_chosen_option = "home";
            this.setNextMenu();
        } else{
            this.last_chosen_option = "unlogged";
        }
    }

    private void setNextMenu(){
        if(this.last_chosen_option.equals("home")) {

            this.next_menu = new HomeMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("unlogged")){

            this.next_menu = new UnloggedMenu(this.app_dependencies);

        }
    }

    public Menu getNextMenu(){
        return this.next_menu;
    }
}
