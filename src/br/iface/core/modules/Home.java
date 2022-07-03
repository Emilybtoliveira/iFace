package br.iface.core.modules;

import br.iface.core.Dependencies;
import br.iface.core.Menu;
import br.iface.core.menus.*;

import java.util.HashMap;
import java.util.Map;

public class Home extends Module {
    private Map<Integer, Menu> menu_map;

    public Home(Dependencies app_dependencies){
        this.menu_map = new HashMap<>();
        this.menu_map.put(1, new UpdateProfileMenu(app_dependencies));
        this.menu_map.put(2, new FriendsManagementMenu(app_dependencies));
        this.menu_map.put(3, new MessagingServiceMenu(app_dependencies));
        this.menu_map.put(4, new CommunityManageMenu(app_dependencies));
        this.menu_map.put(5, new FeedServiceMenu(app_dependencies));
        this.menu_map.put(6, new RemoveAccountMenu(app_dependencies));
        this.menu_map.put(7, new SwitchAccountMenu(app_dependencies));
        this.menu_map.put(8, new Menu(app_dependencies));
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
