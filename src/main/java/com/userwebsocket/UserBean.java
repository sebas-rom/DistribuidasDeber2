package com.userwebsocket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named("userBean")
public class UserBean implements Serializable {

    @Inject
    @Push(channel = "chatChannel")
    PushContext chatChannel;

    @Inject
    @Push(channel = "chatChannel2")
    PushContext chatChannel2;

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    private String currentUser;
    private String currentMessage;
    private List<String> chatChannelMessages = new ArrayList<>();
    private List<String> chatChannel2Messages = new ArrayList<>();

    public void sendMessage(String message, String user, String channel) {
        setCurrentUser(user);
        LocalDateTime now = LocalDateTime.now();
        String formattedMessage = String.format("[%02d:%02d:%02d] %s: %s", now.getHour(), now.getMinute(), now.getSecond(), currentUser, message);

        if ("chatChannel".equals(channel)) {
            chatChannelMessages.add(formattedMessage);
            chatChannel.send(formattedMessage);
        } else if ("chatChannel2".equals(channel)) {
            chatChannel2Messages.add(formattedMessage);
            chatChannel2.send(formattedMessage);
        }

        System.out.println(formattedMessage);
        setCurrentMessage(""); // Clear the currentMessage


    }

    public List<String> getChatChannelMessages() {
        return chatChannelMessages;
    }

    public List<String> getChatChannel2Messages() {
        System.out.println("Loading messages.....");
        return chatChannel2Messages;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }
}
