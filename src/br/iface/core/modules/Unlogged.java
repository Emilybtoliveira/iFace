package br.iface.core.modules;

import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.menus.LoginMenu;
import br.iface.core.menus.SignInMenu;

import java.util.HashMap;
import java.util.Map;

public class Unlogged extends Module {
    private Map<Integer, Menu> menu_map;

    public Unlogged(Dependencies app_dependencies){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, new LoginMenu(app_dependencies));
        this.menu_map.put(2, new SignInMenu(app_dependencies));
        this.menu_map.put(3, new Menu(app_dependencies));
    }

    public String getMenu() {
        return "Escolha uma opcao:\n1. Fazer login\n2. Cadastrar e logar como novo usuário \n3. Sair do programa";
    }

    public Map getMap() {
        return this.menu_map;
       // System.out.println(this.menu_map.get(3));
    }

}
