package com.example.PawsitiveChat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.PawsitiveChat.model.Conversation;
import com.example.PawsitiveChat.model.User;
import com.example.PawsitiveChat.repository.ConversationRepository;
import com.example.PawsitiveChat.repository.UserRepository;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public void saveConversation(String username, String message) {
        User user = findOrCreateUser(username);
    	LocalDateTime date = LocalDateTime.now();
        Conversation conversation = new Conversation(message, date, user);
        conversationRepository.save(conversation);
    }

    public List<Conversation> getConversationsByUser(Integer userId) {
        return conversationRepository.findByUserId(userId);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User findOrCreateUser(String userName) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByName(userName));
        return optionalUser.orElseGet(() -> userRepository.save(new User(userName)));
    }
}
