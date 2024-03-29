package com.userwebsocket;


import java.io.Serializable;
// or import javax.faces.bean.SessionScoped;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("user") // or @ManagedBean(name="user")
@SessionScoped
public class Login implements Serializable {
    boolean alreadyAddedThisUse = false;
    @Inject
    UserBean loggedIn;
    private String name;
    private String password;

    public String getName() { return name; }
    public void setName(String newValue) { name = newValue; }

    public String getPassword() { return password; }
    public void setPassword(String newValue) { password = newValue; }
    public String includeUser() {

        return "chat.xhtml";
    }
    public String logout() {

        return "login.xhtml";
    }
    public String chatroom2() {

        return "chat2.xhtml";
    }
    public String chatroom1() {

        return "chat.xhtml";
    }

}
