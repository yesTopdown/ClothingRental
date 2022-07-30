package com.example.clothingrental.Clothing.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 小仙女
 * @create 2022-07-29 10:50
 */
//@Component
@Slf4j
@ServerEndpoint("/websocket/{usermessage}") //暴露的ws应用的路径
public class WebSocketController {
    /** 当前在线客户端集合（线程安全的）：以键值对方式存储，key是连接的编号，value是连接的对象 */
    private static Map<String ,Session> onlineClientMap = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session,@PathParam("usermessage") String usermessage){
        /*
            do something for onOpen
            与当前客户端连接成功时
         */
    }

    /**
     * 客户端与服务端连接关闭
     * @param session
     * @param usermessage
     */
    @OnClose
    public void onClose(Session session,@PathParam("usermessage") String usermessage){
        /*
            do something for onClose
            与当前客户端连接关闭时
         */
    }

    /**
     * 客户端与服务端连接异常
     * @param error
     * @param session
     * @param usermessage
     */
    @OnError
    public void onError(Throwable error,Session session,@PathParam("usermessage") String usermessage) {
    }

    /**
     * 客户端向服务端发送消息
     * @param usermessage
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMsg(Session session, String message, @PathParam("usermessage") String usermessage) throws IOException {
        /*
            do something for onmessage
            收到来自当前客户端的消息时
         */
        log.info("===message:{}",message);
        sendAllMessage(message);
    }
    //向所有客户端发送消息（广播）
    private void sendAllMessage(String message){
        Set<String> sessionIdSet = onlineClientMap.keySet(); //获得Map的Key的集合
        for (String sessionId : sessionIdSet) { //迭代Key集合
            Session session = onlineClientMap.get(sessionId); //根据Key得到value
            session.getAsyncRemote().sendText(message); //发送消息给客户端
        }
    }
}
