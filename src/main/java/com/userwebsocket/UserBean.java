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
    String message;

    public void sendMessage() {
        LOG.log(Level.INFO, "send push message");
        this.sendPushMessage("hello");
    }

    private void sendPushMessage(Object message) {
        chatChannel.send("" + message + " at " + LocalDateTime.now());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void sendMessage2() {
        // log.log(Level.INFO, "send push message from input box::" + this.message);
        this.sendPushMessage(this.message);
    }
}