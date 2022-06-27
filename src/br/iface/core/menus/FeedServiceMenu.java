package br.iface.core.menus;

import br.iface.controllers.FeedController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.FeedService;
import br.iface.core.modules.Module;
import br.iface.entities.PostMessage;
import br.iface.entities.relationships.UserData;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class FeedServiceMenu extends Menu{
    private Menu next_menu;
    private FeedController feedController;
    private Module feed_module;
    private String last_chosen_option;

    public FeedServiceMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.next_menu = new HomeMenu(app_dependencies);
        this.feedController = new FeedController(app_dependencies.getMainPublicFeed());
        this.feed_module = new FeedService();
        this.last_chosen_option = "";
    }

    public void Menu(){
        System.out.println(feed_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
        this.setNextMenu();
    }

    private void optionOnMap(int option){
        Map menu_map = this.feed_module.getMap();
        this.last_chosen_option = (String) menu_map.get(option);

        if(this.last_chosen_option == null){this.last_chosen_option = "";}
    }

    private void setNextMenu(){
        if(this.last_chosen_option.equals("feed")) {

            feedController.printFeed(app_dependencies.getCurrentUser());

        } else if(this.last_chosen_option.equals("newpost")) {

            feedController.newPost(app_dependencies.getCurrentUser());

        } else if(this.last_chosen_option.equals("myposts")) {

            feedController.printAllMyPosts((UserData) app_dependencies.getCurrentUser());

        } else{
            this.next_menu = new HomeMenu(app_dependencies);
        }

        this.next_menu = new FeedServiceMenu(app_dependencies);
    }

    @Override
    public Menu getNextMenu() {
        return this.next_menu;
    }
}