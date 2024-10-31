package com.internode_studios.spring_boot_application.chatroom.service;

import com.internode_studios.spring_boot_application.chatroom.model.ChatRoom;
import com.internode_studios.spring_boot_application.chatroom.repository.ChatRoomRepository;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

    public ChatRoom initializeChat(Long senderId, Long receiverId) {
        if (!userRepository.existsById(senderId) || !userRepository.existsById(receiverId)) {
            throw new RuntimeException("Both sender and receiver must be valid users.");
        }

        return chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .orElseGet(() -> {
                    ChatRoom newChatRoom = new ChatRoom();
                    newChatRoom.setSenderId(senderId);
                    newChatRoom.setReceiverId(receiverId);
                    return chatRoomRepository.save(newChatRoom);
                });
    }

    public ChatRoom sendMessage(Long chatRoomId, Long senderId, String messageContent) {
        if (!userRepository.existsById(senderId)) {
            throw new RuntimeException("Sender must be a valid user.");
        }

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        Long receiverId = chatRoom.getSenderId().equals(senderId) ? chatRoom.getReceiverId() : chatRoom.getSenderId();

        ChatRoom.Message newMessage = new ChatRoom.Message(senderId, receiverId, messageContent);
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
