package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Message;
import org.example.backend.model.dto.MessageDTO;
import org.example.backend.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    public Message sendMessage(@RequestBody MessageDTO message) {
        return messageService.sendMessage(message.recipientId(),message.senderId(),message.messageContent(),message.messageType());
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{recipientId}")
    public List<Message>  getMessagesForUser(@PathVariable String recipientId) {
        return messageService.getMessagesForUser(recipientId);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{messageId}")
    public Message acceptMessage(@PathVariable String messageId) {
        return messageService.acceptMessage(messageId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/delete/{messageId}")
    public void deleteMessage(@PathVariable String messageId) {
        messageService.deleteMessage(messageId);
    }
}
