package br.iface.core.modules;

import java.util.HashMap;
import java.util.Map;

public class Community extends Module{
    private Map<Integer, String> menu_map;

    public Community(){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, "coms");
        this.menu_map.put(2, "newcom");
    }

    @Override
    public String getMenu() {
        return "Escolha uma opção:\n1. Ver minhas comunidades\n2. Criar nova comunidade" +
                "\nDigite qualquer outro numero para cancelar a operação.";
    }

    @Override
    public Map getMap() {
        return this.menu_map;
    }
}
