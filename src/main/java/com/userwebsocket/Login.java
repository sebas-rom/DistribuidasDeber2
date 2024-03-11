package com.userwebsocket;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
// or import javax.faces.bean.SessionScoped;

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
        loggedIn.addUser(name);
        return "chat.xhtml";
    }
    public String logout() {
        loggedIn.removeUser(name);
        return "login.xhtml";
    }

}
