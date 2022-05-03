package br.iface.entities;

import java.util.List;
import java.util.ArrayList;

public abstract class User {
    private String login;
    private String password;
    private String name;

    public User(){};

    public User(String login, String password, String name){
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getLogin(){
        return this.login;
    }

    public boolean setLogin(String login){
        this.login = login;
        return true;
    }

    public String getPass(){
        return this.password;
    }

    public boolean setPass(String password){
        this.password = password;
        return true;
    }

    public String getName(){
        return this.name;
    }

    public boolean setName(String name){
        this.name = name;
        return true;
    }

    @Override
    public String toString() {
        return "Login: "+ getLogin() + "Senha: "+getPass()+ "Nome: "+getName();
    }
}
