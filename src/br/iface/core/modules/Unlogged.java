package br.iface.core.modules;

import java.util.HashMap;
import java.util.Map;

public class Unlogged extends Module {
    private Map<Integer, String> menu_map;

    public Unlogged(){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, "login");
        this.menu_map.put(2, "signin");
        this.menu_map.put(3, "quit");
    }

    public String getMenu() {
        return "Escolha uma opcao:\n1. Fazer login\n2. Cadastrar e logar como novo usuário \n3. Sair do programa";
    }

    public Map getMap() {
        return this.menu_map;
       // System.out.println(this.menu_map.get(3));
    }

}
