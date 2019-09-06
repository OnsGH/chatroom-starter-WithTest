package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;

/**
 * WebSocket message model
 */
public class Message {


   public Message() {};

    public Message(String username, String content, MessageType type, int onlineCount) {
        super();
        this.username = username;
        this.content=content;
        this.type=type;
        this.onlineCount = onlineCount;


    };

    public Message(MessageType type, String username, String content, int onlineCount ) {
        this.username = username;
        this.content=content;
        this.type=type;
        this.onlineCount=onlineCount;
    };


    public enum MessageType { JOIN, SPEAK,  LEAVE }

    private String username;

    private String content ;

    private MessageType type;

    private int onlineCount ;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public int getOnlineCount() { return onlineCount; }

    public void setOnlineCount(int onlineCount) { this.onlineCount = onlineCount; }


    public static String jsonStr(MessageType type, String username, String content, int onlineCount) {

       return  JSON.toJSONString(new Message(type,username,content,onlineCount));

    }
    // TODO: add message model.


}
