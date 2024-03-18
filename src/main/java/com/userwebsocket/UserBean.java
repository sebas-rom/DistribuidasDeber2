package com.userwebsocket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
    private String recipient;
    private String currentMessage;
    private String currentPrivateMessage;
    private List<String> privateMessages; // List to store private messages
    private List<String> connectedUsers;


    public UserBean() {
        connectedUsers = new ArrayList<>();
    }


    public void sendGlobalMessage(String message, String user) {
    setCurrentUser(user);
    String formattedMessage =   currentUser + " ("+LocalDateTime.now().getHour()+":" + LocalTime.now().getMinute()+"):  " + message  ;
    globalChat.send(formattedMessage);
    System.out.println(formattedMessage);
    setCurrentMessage(""); // Clear the currentMessage
    }

    public void sendPrivateMessage(String message, String recipient) {
        //todo
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
}

    public void addConnectedUser(String user) {
        connectedUsers.add(user);
    }

    public void removeConnectedUser(String user) {
        connectedUsers.remove(user);
    }

    public List<String> getConnectedUsers() {
        return connectedUsers;
    }

    public void setPrivateMessages(List<String> privateMessages) {
    this.privateMessages = privateMessages;
}

    public List<String> getPrivateMessages() {
        return privateMessages;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}