package com.internode_studios.spring_boot_application.chatroom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;   // Changed from String to Long
    private Long receiverId; // Changed from String to Long

    @ElementCollection
    @CollectionTable(name = "chat_room_messages", joinColumns = @JoinColumn(name = "chat_room_id"))
    private List<Message> messages = new ArrayList<>();

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private Long senderId;    // Changed from String to Long
        private Long receiverId;  // Changed from String to Long
        private String message;

        @Temporal(TemporalType.TIMESTAMP)
        private Date timestamp = new Date();

        public Message(Long senderId, Long receiverId, String message) {
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.message = message;
            this.timestamp = new Date(); // Set timestamp to current date
        }
    }
}
