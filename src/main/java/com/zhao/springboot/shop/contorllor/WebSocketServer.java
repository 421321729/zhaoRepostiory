package com.zhao.springboot.shop.contorllor;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/webSocket/{userId}")
public class WebSocketServer {
    static Integer count=0;
    private Session session;
    private String userId;
    private static ConcurrentHashMap<String,WebSocketServer> webSocketServerMap=new ConcurrentHashMap<>();
//private static ConcurrentHashMap<String,Session> webSocketServerMap=new ConcurrentHashMap<>();
    private static synchronized void countIncrease(){
        count++;
    }
    private static synchronized void countSub(){
        count--;
    }
    public static synchronized int getCount(){
        return count;
    }
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId,@PathParam("userId")String userId){
        this.session=session;
        this.userId=userId;
        if(webSocketServerMap.containsKey(userId)){
            webSocketServerMap.remove(userId);
            webSocketServerMap.put(userId, this);
        }else {
            webSocketServerMap.put(userId, this);
            countIncrease();;
        }
        System.out.println("用户连接:"+userId+",当前人数"+WebSocketServer.getCount());
    }
    @OnClose
    public void onClose(){
        if(webSocketServerMap.containsKey(userId)){
            webSocketServerMap.remove(userId);
            countSub();
        }
        System.out.println("用户退出"+userId+",当前在线人数"+WebSocketServer.getCount());
    }
    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        System.out.println("用户信息"+userId+",内容"+message);
        sendAll("用户"+userId+"说:"+message);
        //webSocketServerMap.get(userId).sendMessage("收到");
        //this.sendAll(message);
    }
    @OnError
    public void onError(Session session,Throwable throwable){

        System.out.println("用户错误"+throwable.getMessage());
        throwable.printStackTrace();
    }
    public void sendMessage(String Message) throws IOException {
        this.session.getBasicRemote().sendText(Message);
    }
     public void sendAll(String message) throws IOException {
      for(String userId: webSocketServerMap.keySet()){
          webSocketServerMap.get(userId).sendMessage(message);
//          webSocketServerMap.get(userId).getBasicRemote().sendText(message);
      }
     }
}
