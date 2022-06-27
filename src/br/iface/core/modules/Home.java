package br.iface.core.modules;

import java.util.HashMap;
import java.util.Map;

public class Home extends Module {
    private Map<Integer, String> menu_map;

    public Home(){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, "update");
        this.menu_map.put(2, "friendsmanage");
        this.menu_map.put(3, "chats");
        this.menu_map.put(4, "community");
        this.menu_map.put(5, "feed");
        this.menu_map.put(6, "delete");
        this.menu_map.put(7, "switchacc");
        this.menu_map.put(8, "quit");
        //System.out.println(this.menu_map.get(3));
    }

    public String getMenu() {
        return "Escolha uma opção:\n1. Alterar informações do usuário atual\n" +
                "2. Gerenciar amizades\n3. Chats\n4. Comunidades\n5. Feed\n6. Excluir minha conta\n7. Trocar de conta" +
                "\n8. Sair do programa";
    }

    public Map getMap() {
        return this.menu_map;
    }

}
