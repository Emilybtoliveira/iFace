package br.iface.core.menus;

import br.iface.controllers.LogInController;
import br.iface.controllers.MessagingController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Messaging;
import br.iface.core.modules.Module;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class MessagingServiceMenu extends Menu {
    private Module messaging_module;
    private Menu next_menu;
    private MessagingController messagingController;
    private String last_chosen_option;

    public MessagingServiceMenu(Dependencies app_dependencies) {
        super(app_dependencies);
        this.messagingController = new MessagingController(app_dependencies.getUsers());
        this.messaging_module = new Messaging();
        this.last_chosen_option = "";
    }

    @Override
    public void Menu(){
        System.out.println(messaging_module.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
        this.setNextMenu();
    }

    private void optionOnMap(int option){
        Map menu_map = this.messaging_module.getMap();
        this.last_chosen_option = (String) menu_map.get(option);

        if(this.last_chosen_option == null){this.last_chosen_option = "";}
    }

    public void setNextMenu(){
        this.next_menu = new MessagingServiceMenu(app_dependencies);

        if (this.last_chosen_option.equals("chats")) {
            messagingController.showActiveChats(app_dependencies.getCurrentUser());
        } else if (this.last_chosen_option.equals("newchat")) {
            messagingController.startAChat(app_dependencies.getCurrentUser());
        } else{
            this.next_menu = new HomeMenu(app_dependencies);
        }
    }

    @Override
    public Menu getNextMenu() {
        return this.next_menu;
    }
}
