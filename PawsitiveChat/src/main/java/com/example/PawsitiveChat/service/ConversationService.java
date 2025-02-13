package com.example.PawsitiveChat.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.PawsitiveChat.model.Conversation;
import com.example.PawsitiveChat.repository.ConversationRepository;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    public void saveConversation(String userName, String message) {
        LocalDateTime date = LocalDateTime.now();
        Conversation conversation = new Conversation(userName, message, date);
        conversationRepository.save(conversation);
    }

    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }
}
