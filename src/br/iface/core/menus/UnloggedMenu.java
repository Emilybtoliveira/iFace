package br.iface.core.menus;

import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Module;
import br.iface.core.modules.Unlogged;

import java.util.Map;

public class UnloggedMenu extends Menu {
    private Module unlogged_module;
    private String last_chosen_option;
    private Menu next_menu;

    public UnloggedMenu(Dependencies app_dependencies){
        super(app_dependencies);
        this.unlogged_module = new Unlogged();
        this.last_chosen_option = "";
    }

    public void Menu(){
        System.out.println(unlogged_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
        this.setNextMenu();
    }

    private void optionOnMap(int option){
        Map menu_map = this.unlogged_module.getMap();
        this.last_chosen_option = (String) menu_map.get(option);

        if(this.last_chosen_option == null){this.last_chosen_option = "";}
    }

    private void setNextMenu(){
        if(this.last_chosen_option.equals("login")) {

            this.next_menu = new LoginMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("signin")){

                this.next_menu = new SignInMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("quit")) {
            this.next_menu = null;
        }
        else{
            this.next_menu = new UnloggedMenu(this.app_dependencies);
        }
    }

    public Menu getNextMenu(){
        return this.next_menu;
    }
}
