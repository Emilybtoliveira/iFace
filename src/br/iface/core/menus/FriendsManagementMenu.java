package br.iface.core.menus;

import br.iface.controllers.FriendsController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Friends;

import java.util.Map;

import br.iface.core.modules.Module;


public class FriendsManagementMenu extends Menu{
    private Menu next_menu;
    private FriendsController friendsController;
    private Module friends_module;
    private String last_chosen_option;

    public FriendsManagementMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.friendsController = new FriendsController(app_dependencies.getUsers());
        this.friends_module = new Friends();
        this.last_chosen_option = "";
    }

    public void Menu(){
        System.out.println(friends_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
        this.setNextMenu();
    }

    private void optionOnMap(int option){
        Map menu_map = this.friends_module.getMap();
        this.last_chosen_option = (String) menu_map.get(option);

        if(this.last_chosen_option == null){this.last_chosen_option = "";}
    }

    protected void setNextMenu(){
        this.next_menu = new FriendsManagementMenu(app_dependencies);

        if(this.last_chosen_option.equals("requests")) {

           friendsController.manageFriendsRequests(app_dependencies.getCurrentUser());

       } else if(this.last_chosen_option.equals("newfriend")) {

           friendsController.newFriendRequest(app_dependencies.getCurrentUser());

       } else if(this.last_chosen_option.equals("myfriends")) {

           friendsController.showMyFriends(app_dependencies.getCurrentUser());

       }else{
           this.next_menu = new HomeMenu(app_dependencies);
       }

    }

    @Override
    public Menu getNextMenu() {
        return this.next_menu;
    }
}
