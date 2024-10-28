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

    private String senderId;

    private String receiverId;

    @ElementCollection
    @CollectionTable(name = "chat_room_messages", joinColumns = @JoinColumn(name = "chat_room_id"))
    private List<Message> messages = new ArrayList<>();

    @Embeddable
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {
        private String sender;
        private String message;

        @Temporal(TemporalType.TIMESTAMP)
        private Date timestamp = new Date();

        // Constructor for convenience
        public Message(String sender, String message) {
            this.sender = sender;
            this.message = message;
            this.timestamp = new Date(); // Set timestamp to current date
        }
    }
}
