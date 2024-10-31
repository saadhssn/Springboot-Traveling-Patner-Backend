package com.internode_studios.spring_boot_application.chatroom.service;

import com.internode_studios.spring_boot_application.chatroom.model.ChatRoom;
import com.internode_studios.spring_boot_application.chatroom.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ChatRoom sendMessage(Long chatRoomId, String senderId, String messageContent, String message) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        // Determine the receiverId based on existing chat room participants
        String receiverId = chatRoom.getSenderId().equals(senderId) ? chatRoom.getReceiverId() : chatRoom.getSenderId();

        // Log for debugging
        System.out.println("Sender ID: " + senderId + ", Receiver ID: " + receiverId + ", Message: " + messageContent);

        // Create a new message with both senderId and receiverId
        ChatRoom.Message newMessage = new ChatRoom.Message(senderId, receiverId, messageContent);
        chatRoom.getMessages().add(newMessage);

        // Save the updated chat room
        return chatRoomRepository.save(chatRoom);
    }



    public ChatRoom getChatRoomMessages(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("No previous conversation found for this room"));
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }


//    public MessageResponseDTO sendMessage(Long chatRoomId, String senderId, String message) {
//        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
//                .orElseThrow(() -> new RuntimeException("Chat room not found"));
//
//        // Create and add the new message to the chat room
//        ChatRoom.Message newMessage = new ChatRoom.Message(senderId, message, new Date());
//        chatRoom.getMessages().add(newMessage);
//        chatRoomRepository.save(chatRoom);
//
//        // Return the message as a MessageResponseDTO with additional details
//        return new MessageResponseDTO(chatRoom.getSenderId(), chatRoom.getReceiverId(), newMessage.getMessage(), newMessage.getTimestamp());
//    }


}
