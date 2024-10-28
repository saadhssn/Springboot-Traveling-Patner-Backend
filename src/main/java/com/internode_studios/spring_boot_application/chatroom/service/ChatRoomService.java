package com.internode_studios.spring_boot_application.chatroom.service;

import com.internode_studios.spring_boot_application.chatroom.model.ChatRoom;
import com.internode_studios.spring_boot_application.chatroom.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatRoom initializeChat(String senderId, String receiverId) {
        return chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .orElseGet(() -> {
                    ChatRoom newChatRoom = new ChatRoom();
                    newChatRoom.setSenderId(senderId);
                    newChatRoom.setReceiverId(receiverId);
                    return chatRoomRepository.save(newChatRoom);
                });
    }

    public ChatRoom sendMessage(Long chatRoomId, String senderId, String message) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        ChatRoom.Message newMessage = new ChatRoom.Message(senderId, message, new Date());
        chatRoom.getMessages().add(newMessage);
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getChatRoomMessages(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("No previous conversation found for this room"));
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }
}
