package br.iface.core.modules;

import br.iface.core.Dependencies;
import br.iface.core.menus.UpdateProfileMenu;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends Module{
    private Map<Integer, String> menu_map;

    public UpdateProfile(){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, "login");
        this.menu_map.put(2, "pass");
        this.menu_map.put(3, "name");
        this.menu_map.put(4, "quit");
    }
    @Override
    public String getMenu() {
        return "Qual informação você quer modificar?\n1. Login\n2. Senha\n3. Nome\n4. Nenhuma";
    }

    @Override
    public Map getMap() {
        return this.menu_map;
    }
}
