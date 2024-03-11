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
import java.util.logging.Level;
import java.util.logging.Logger;

//@formatter:off

@ViewScoped
@Named("userBean")
public class UserBean implements Serializable {
    @Inject
    @Push
    PushContext chatChannel;
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    private String currentUser;
    private String currentMessage;




    public void sendMessage(String message, String user) {
        setCurrentUser(user);
        String formattedMessage = LocalDateTime.now().getHour() + " - " + currentUser + ": " + message;
        chatChannel.send(formattedMessage);
        System.out.println(formattedMessage);
    }
    public String getCurrentUser() {
        return this.currentUser;
    }
    public void setCurrentUser(String currentUser){
        this.currentUser=currentUser;
    }

    public String getCurrentMessage() {
        return this.currentMessage;
    }public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

}