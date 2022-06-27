package br.iface.core.modules;

import java.util.HashMap;
import java.util.Map;

public class FeedService extends Module{
    private Map<Integer, String> menu_map;

    public FeedService(){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, "feed");
        this.menu_map.put(2, "newpost");
        this.menu_map.put(3, "myposts");
    }

    @Override
    public String getMenu() {
        return "Escolha uma opção:\n1. Ver feed\n2. Criar novo post\n3. Ver meus posts" +
                "\nDigite qualquer outro número para cancelar a operação.";
    }

    @Override
    public Map getMap() {
        return this.menu_map;
    }
}
