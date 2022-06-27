package br.iface.core.modules;

import java.util.HashMap;
import java.util.Map;

public class Messaging extends Module{
    private Map<Integer, String> menu_map;

    public Messaging(){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, "chats");
        this.menu_map.put(2, "newchat");
    }

    @Override
    public String getMenu() {
        return "Escolha uma opção:\n1. Ver minhas conversas\n2. Iniciar um novo chat\nDigite qualquer outro número para voltar.";
    }

    @Override
    public Map getMap() {
        return this.menu_map;
    }
}
