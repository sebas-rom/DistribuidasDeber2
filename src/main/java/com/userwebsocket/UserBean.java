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

    // Push more channels if we need more chatrooms
    @Inject
    @Push(channel = "chatChannel")
    PushContext chatChannel;

    @Inject
    @Push(channel = "chatChannel2")
    PushContext chatChannel2;
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    private String currentUser;
    private String currentMessage;



    public void sendMessage(String message, String user, String channel) {
    setCurrentUser(user);
    String formattedMessage = LocalDateTime.now().getHour() + " - " + currentUser + ": " + message;
    // select appropiate channel to send the message
    // assure that the function call on chat.xhtml passes the correct channel string
    if ("chatChannel".equals(channel)) {
        chatChannel.send(formattedMessage);
    } else if ("chatChannel2".equals(channel)) {
        chatChannel2.send(formattedMessage);
    }
    System.out.println(formattedMessage);
//    System.out.println(" ->"+ channel); // for debugging
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