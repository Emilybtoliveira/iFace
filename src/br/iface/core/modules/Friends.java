package br.iface.core.modules;

import java.util.HashMap;
import java.util.Map;

public class Friends extends Module{
    private Map<Integer, String> menu_map;

    public Friends(){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, "requests");
        this.menu_map.put(2, "newfriend");
        this.menu_map.put(3, "myfriends");
    }

    @Override
    public String getMenu() {
        return "Escolha uma opção:\n1. Ver minhas solicitações recebidas\n" +
                "2. Enviar nova solicitação de amizade.\n3. Ver minhas amizades\nDigite qualquer outro numero para cancelar a operação.";
    }

    @Override
    public Map getMap() {
        return this.menu_map;
    }
}
