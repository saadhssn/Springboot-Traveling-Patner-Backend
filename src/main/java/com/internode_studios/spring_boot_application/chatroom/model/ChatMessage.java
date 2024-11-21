package com.internode_studios.spring_boot_application.chatroom.model;

import lombok.Data;

@Data
public class ChatMessage {
    private Long chatRoomId; // Use Long instead of String for the chat room ID
    private String senderId;
    private String receiverId; // Added receiverId to track the recipient
    private String message;

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
