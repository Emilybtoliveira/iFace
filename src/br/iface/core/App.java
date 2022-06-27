package br.iface.core;

import br.iface.core.menus.UnloggedMenu;
import br.iface.core.modules.Module;
import br.iface.core.modules.Unlogged;

public class App {
    private Dependencies app_dependencies;
    private Menu app_menu;

    public App(){
        this.app_dependencies =  new Dependencies();
        this.app_menu = new UnloggedMenu(this.app_dependencies);
    }

    public void startApp(){
        while(true) {
            this.app_menu.Menu();
            this.app_menu = this.app_menu.getNextMenu();

            if(app_menu == null){
                System.out.println("quiting...");
                return;
            }
        }
    }
}
