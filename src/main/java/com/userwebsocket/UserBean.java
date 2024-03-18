package com.userwebsocket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

//@formatter:off

@ApplicationScoped
@Named("userBean")
public class UserBean implements Serializable {
    @Inject
    @Push
    PushContext globalChat;
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    private String currentUser;
    private String currentMessage;

    private String currentPrivateMessage;


    public void sendGlobalMessage(String message, String user) {
    setCurrentUser(user);
    String formattedMessage =   currentUser + " ("+LocalDateTime.now().getHour()+":" + LocalTime.now().getMinute()+"):  " + message  ;
    globalChat.send(formattedMessage);
    System.out.println(formattedMessage);
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
    }
    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String getCurrentPrivateMessage() {
    return currentPrivateMessage;
}
    public void setCurrentPrivateMessage(String currentPrivateMessage) {
    this.currentPrivateMessage = currentPrivateMessage;
}}