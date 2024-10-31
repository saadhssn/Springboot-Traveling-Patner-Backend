package com.internode_studios.spring_boot_application.chatroom.model;

import lombok.Data;

@Data
public class ChatMessage {
    private Long chatRoomId;
    private Long senderId;    // Changed from String to Long
    private Long receiverId;  // Changed from String to Long
    private String message;

    // Getters and Setters

}
