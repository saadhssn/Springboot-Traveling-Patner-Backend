package com.internode_studios.spring_boot_application.sockets;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.internode_studios.spring_boot_application.chatroom.model.ChatMessage;
import com.internode_studios.spring_boot_application.chatroom.model.ChatRoom;
import com.internode_studios.spring_boot_application.chatroom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SocketEventListener {

    @Autowired
    private SocketIOServer socketIOServer;

    @Autowired
    private ChatRoomService chatRoomService;

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        socketIOServer.addListeners(new DataListener<ChatMessage>() {
            @Override
            public void onData(SocketIOClient client, ChatMessage data, AckRequest ackRequest) throws Exception {
                ChatRoom chatRoom = chatRoomService.sendMessage(data.getChatRoomId(), data.getSenderId(), data.getMessage());
                socketIOServer.getBroadcastOperations().sendEvent("chat", chatRoom);
            }
        });
        socketIOServer.start();
    }
}