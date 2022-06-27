package br.iface.core;

import br.iface.core.modules.Module;
import br.iface.validations.InvalidInputException;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    protected Dependencies app_dependencies;
    protected Scanner input;
    private String last_chosen_option;

    public Menu(Dependencies app_dependencies){
        this.app_dependencies = app_dependencies;
        this.input = new Scanner(System.in);
        this.last_chosen_option = "";
    }

    public void Menu(){
    }

    public int getUserChoice(){
        try{
            int op = this.input.nextInt();
            return op;
        } catch (InputMismatchException e) {
            input.next(); //limpa o buffer
            System.out.println("Você precisa inserir um número.\n");

            return -1;
        }
    }

    private void setNextMenu(){}

    private void optionOnMap(int option, Module current_module){
        Map menu_map = current_module.getMap();
        this.last_chosen_option = (String) menu_map.get(option);

        if(this.last_chosen_option == null){this.last_chosen_option = "";}
    }

    public Menu getNextMenu(){return null;}
}
