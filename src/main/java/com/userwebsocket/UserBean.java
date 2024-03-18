package com.userwebsocket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    System.out.println(" ->"+ chatChannel);
    setCurrentMessage(""); // Clear the currentMessage
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