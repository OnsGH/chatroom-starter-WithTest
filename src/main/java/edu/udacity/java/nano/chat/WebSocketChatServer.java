package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat/{username}")
//@ServerEndpoint("/chat")

public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private Session session ;
    public int onlineUsersCount = 0;
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String message) {
        //TODO: add send message method.
        System.out.println("send");

        onlineSessions.forEach((sessionId, session) ->  {
            try {

                session.getBasicRemote().sendText(message);

            } catch (IOException e) {

                e.printStackTrace();

            }

        });

    }


    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen (Session session,@PathParam("username") String username)
        throws IOException {
            //TODO: add on open connection.
        System.out.println("open"+session.getId());
        onlineSessions.put(session.getId(), session);
        sendMessageToAll(Message.jsonStr(Message.MessageType.JOIN, username, "Joined !", onlineSessions.size()));


    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */




    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        System.out.println("jsonStr "+jsonStr);
        Message message = JSON.parseObject(jsonStr, Message.class);

        sendMessageToAll(Message.jsonStr(Message.MessageType.SPEAK, message.getUsername(), message.getContent() , onlineSessions.size()));
     }

    /**
     * Close connection, 1) remove session, 2) update user.
     */

    @OnClose
    public void onClose(Session session,  @PathParam("username") String username) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());

        sendMessageToAll(Message.jsonStr(Message.MessageType.LEAVE, username, "Left ！", onlineSessions.size()));


    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
