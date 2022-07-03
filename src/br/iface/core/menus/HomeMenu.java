package br.iface.core.menus;

import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Home;
import br.iface.core.modules.Module;

import java.util.Map;

public class HomeMenu extends Menu {
    private Module home_module;
    private Menu next_menu;

    public HomeMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.home_module = new Home(app_dependencies);
    }

    public void Menu(){
        System.out.printf("Você está navegando como %s.\n", app_dependencies.getCurrentUser().getName());
        System.out.println(home_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);

    }

    private void optionOnMap(int option){
        Map menu_map = this.home_module.getMap();
        this.next_menu = (Menu) menu_map.get(option);

        if(this.next_menu == null){this.next_menu = new HomeMenu(app_dependencies);}
    }


    public Menu getNextMenu(){
        return this.next_menu;
    }
}
