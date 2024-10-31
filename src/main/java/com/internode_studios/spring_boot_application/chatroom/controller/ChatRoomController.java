package com.internode_studios.spring_boot_application.chatroom.controller;

import com.internode_studios.spring_boot_application.chatroom.model.ChatMessage;
import com.internode_studios.spring_boot_application.chatroom.model.ChatRoom;
import com.internode_studios.spring_boot_application.chatroom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("/initializechat")
    public ResponseEntity<ChatRoom> initializeChat(@RequestBody ChatRoom chatRoom) {
        ChatRoom initializedChatRoom = chatRoomService.initializeChat(chatRoom.getSenderId(), chatRoom.getReceiverId());
        return ResponseEntity.ok(initializedChatRoom);
    }

    @PostMapping("/messages/{chatRoomId}")
    public ResponseEntity<ChatRoom> sendMessage(@PathVariable Long chatRoomId, @RequestBody ChatMessage messageRequest) {
        ChatRoom updatedChatRoom = chatRoomService.sendMessage(chatRoomId, messageRequest.getSenderId(), messageRequest.getMessage());
        return ResponseEntity.ok(updatedChatRoom);
    }

    @GetMapping("/messages/{chatRoomId}")
    public ResponseEntity<ChatRoom> getChatRoomMessages(@PathVariable Long chatRoomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoomMessages(chatRoomId);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/chatrooms")
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomService.getAllChatRooms();
        return ResponseEntity.ok(chatRooms);
    }
}
