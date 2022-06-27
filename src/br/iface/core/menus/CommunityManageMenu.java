package br.iface.core.menus;

import br.iface.controllers.CommunityController;
import br.iface.controllers.LogInController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Community;
import br.iface.core.modules.Friends;
import br.iface.core.modules.Module;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CommunityManageMenu extends Menu{
    private Menu next_menu;
    private CommunityController communityController;
    private Module community_module;
    private String last_chosen_option;

    public CommunityManageMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.next_menu = new HomeMenu(app_dependencies);
        this.communityController = new CommunityController(app_dependencies.getUsers());
        this.community_module = new Community();
    }

    public void Menu(){
        System.out.println(community_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
        this.setNextMenu();
    }

    private void optionOnMap(int option){
        Map menu_map = this.community_module.getMap();
        this.last_chosen_option = (String) menu_map.get(option);

        if(this.last_chosen_option == null){this.last_chosen_option = "";}
    }

    private void setNextMenu(){
        if (last_chosen_option.equals("coms")) {
            communityController.selectACommunity(app_dependencies.getCurrentUser());
        } else if (last_chosen_option.equals("newcom")) {
            communityController.createACommunity(app_dependencies.getCurrentUser());
        } else{
            this.next_menu = new HomeMenu(app_dependencies);
        }
        this.next_menu = new CommunityManageMenu(app_dependencies);
    }

    @Override
    public Menu getNextMenu() {
        return this.next_menu;
    }
}
