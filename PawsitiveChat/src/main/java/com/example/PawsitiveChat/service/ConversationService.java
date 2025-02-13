package com.example.PawsitiveChat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.PawsitiveChat.model.Conversation;
import com.example.PawsitiveChat.model.User;
import com.example.PawsitiveChat.repository.ConversationRepository;
import com.example.PawsitiveChat.repository.UserRepository;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final String quoteApiUrl = "http://localhost:8181/api/quote";
    
    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository, RestTemplate restTemplate) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public void saveConversation(String username, String message) {
        String quote = getQuote();
    	User user = findOrCreateUser(username);
    	LocalDateTime date = LocalDateTime.now();
        Conversation conversation = new Conversation(message, quote, date, user);
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
    
    public String getQuote() {
    	try {
            QuoteResponse quoteResponse = restTemplate.getForObject(quoteApiUrl, QuoteResponse.class);
            return quoteResponse != null ? quoteResponse.getContent() : "Citation par défaut : La vie est belle.";
        } catch (HttpStatusCodeException e) {
            return "Désolé, une erreur est survenue lors de la récupération de la citation.";
        } catch (Exception e) {
            return "Désolé, un problème technique est survenu.";
        }
    }
    
    private static class QuoteResponse {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
