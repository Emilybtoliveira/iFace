package br.iface.core.menus;

import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Module;
import br.iface.core.modules.Unlogged;

import java.util.Map;

public class UnloggedMenu extends Menu {
    private Module unlogged_module;
    private Menu next_menu;

    public UnloggedMenu(Dependencies app_dependencies){
        super(app_dependencies);
        this.unlogged_module = new Unlogged(app_dependencies);
    }

    public void Menu(){
        System.out.println(unlogged_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
    }

    private void optionOnMap(int option){
        Map menu_map = this.unlogged_module.getMap();
        this.next_menu = (Menu) menu_map.get(option);

        if(this.next_menu == null){this.next_menu = new UnloggedMenu(app_dependencies);}
    }


    public Menu getNextMenu(){
        return this.next_menu;
    }
}
