package com.rzp.entity.websocket;

import cn.hutool.json.JSONUtil;
import com.rzp.entity.pojo.Message;
import com.rzp.entity.utils.MessageUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{userName}")
@Component
public class ChatEndpoint {

    private static Map<String,Session> onlineUsers = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userName")String userName){
        onlineUsers.put(userName,session);

        // 1. 获取消息.
        String message = MessageUtils.getMessage(true, null, getNames());
        // 2. 调用方法进行系统消息推送
        broadCastAllUsers(message);
    }

    // 发送广播消息
    private void broadCastAllUsers(String message){
        try{
            Set<String> names = onlineUsers.keySet();
            for (String name : names) {
                Session session = onlineUsers.get(name);
                session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 获得在线的用户全部得名字
    private Set<String> getNames(){
        return onlineUsers.keySet();
    }

    // 接收到消息时
    @OnMessage
    public void OnMessage(String message, @PathParam("userName") String userName) throws IOException {
        Message m = JSONUtil.toBean(message, Message.class);
        String toName = m.getToName();
        // 获取推送的用户session
        Session session = onlineUsers.get(toName);

        // 获得发送对象消息.
        String sendMessage = MessageUtils.getMessage(false, userName, m.getMessage());
        // 通过session给指定的用户发送消息.
        session.getBasicRemote().sendText(sendMessage);
    }

    @OnClose
    public void OnClose(@PathParam("userName") String userName){
        onlineUsers.remove(userName);
    }


}
