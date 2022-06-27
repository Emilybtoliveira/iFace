package br.iface.core.menus;

import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Home;
import br.iface.core.modules.Module;

import java.util.Map;

public class HomeMenu extends Menu {
    private Module home_module;
    private String last_chosen_option;
    private Menu next_menu;

    public HomeMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.home_module = new Home();
        this.last_chosen_option = "";
    }

    public void Menu(){
        System.out.printf("Você está navegando como %s.\n", app_dependencies.getCurrentUser().getName());
        System.out.println(home_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
        this.setNextMenu();
    }

    private void optionOnMap(int option){
        Map menu_map = this.home_module.getMap();
        this.last_chosen_option = (String) menu_map.get(option);

        if(this.last_chosen_option == null){this.last_chosen_option = "";}
    }

    public void setNextMenu(){
        if(this.last_chosen_option.equals("update")) {

            this.next_menu = new UpdateProfileMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("friendsmanage")) {

            this.next_menu = new FriendsManagementMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("chats")) {

            this.next_menu = new MessagingServiceMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("community")) {

            this.next_menu = new CommunityManageMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("feed")) {
            this.next_menu = new FeedServiceMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("delete")) {

            this.next_menu = new RemoveAccountMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("switchacc")) {

            this.next_menu = new SwitchAccountMenu(this.app_dependencies);

        } else if(this.last_chosen_option.equals("quit")) {

            this.next_menu = null;

        } else{

            this.next_menu = new HomeMenu(app_dependencies);

        }
    }

    public Menu getNextMenu(){
        return this.next_menu;
    }
}
