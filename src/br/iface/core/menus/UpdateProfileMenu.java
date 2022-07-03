package br.iface.core.menus;

import br.iface.controllers.UpdateProfileController;
import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.modules.Module;
import br.iface.core.modules.UpdateProfile;

import java.util.Map;

public class UpdateProfileMenu extends Menu {
    private Menu next_menu;
    private Module updateProfileModule;
    private String last_chosen_option;
    private UpdateProfileController updateProfileController;

    public UpdateProfileMenu(Dependencies app_dependencies){
        super(app_dependencies);
        this.updateProfileModule = new UpdateProfile();
        this.updateProfileController = new UpdateProfileController(app_dependencies);
        this.last_chosen_option = "";
    }

    public void Menu(){
        System.out.printf("Essas são suas informações: %s\n", app_dependencies.getCurrentUser());

        System.out.println(updateProfileModule.getMenu());
        int chosen_int_option = super.getUserChoice();

        this.optionOnMap(chosen_int_option);
        this.setNextMenu();
    }

    private void optionOnMap(int option){
        Map menu_map = this.updateProfileModule.getMap();
        this.last_chosen_option = (String) menu_map.get(option);
    }

    private void setNextMenu(){
        if(!this.last_chosen_option.equals("quit")) {
            if (this.last_chosen_option != null) {
                updateProfileController.executeUpdate(last_chosen_option);
            }

            this.next_menu = new UpdateProfileMenu(app_dependencies);

        }else{
            System.out.printf("Alterações feitas com sucesso!\n\n", app_dependencies.getCurrentUser());

            System.out.println(app_dependencies.getCurrentUser());
            this.next_menu = new HomeMenu(app_dependencies);
        }
    }

    public Menu getNextMenu(){
        return this.next_menu;
    }
}
