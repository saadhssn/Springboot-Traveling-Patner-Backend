package com.internode_studios.spring_boot_application.chatroom.model;

import lombok.Data;

@Data
public class ChatMessage {
    private Long chatRoomId;  // Use Long instead of String for the chat room ID
    private String senderId;
    private String message;
}