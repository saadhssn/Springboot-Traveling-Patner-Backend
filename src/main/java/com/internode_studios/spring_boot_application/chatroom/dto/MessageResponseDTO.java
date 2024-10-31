package com.internode_studios.spring_boot_application.chatroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MessageResponseDTO {
    private String senderId;
    private String receiverId;
    private String message;
    private Date timestamp;
}
