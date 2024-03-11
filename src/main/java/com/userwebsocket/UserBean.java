package com.userwebsocket;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;import javax.inject.Named;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

//@formatter:off

@ApplicationScoped
@Named
public class UserBean implements Serializable {
    private List<String> messages = new ArrayList<>();
    private List<String> users = new ArrayList<>();
    private String currentUser;

    @Push
    private PushContext pushContext;

    public void sendMessage(String message) {
        String formattedMessage = LocalDateTime.now() + " - " + currentUser + ": " + message;
        messages.add(formattedMessage);
        pushContext.send(formattedMessage);
    }

    public void addUser(String userName) {
        if (!users.contains(userName)) {
            users.add(userName);
            currentUser = userName;
        }
    }

    public void removeUser(String userName) {
        users.remove(userName);
        if (currentUser.equals(userName)) {
            currentUser = null;
        }
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}