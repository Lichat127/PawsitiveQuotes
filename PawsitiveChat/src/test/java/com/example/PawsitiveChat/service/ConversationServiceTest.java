package com.example.PawsitiveChat.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Sort;

import com.example.PawsitiveChat.model.Conversation;
import com.example.PawsitiveChat.model.User;
import com.example.PawsitiveChat.repository.ConversationRepository;
import com.example.PawsitiveChat.repository.UserRepository;

class ConversationServiceTest {

    @Mock
    private ConversationRepository conversationRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RestTemplate restTemplate;

    private ConversationService conversationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        conversationService = new ConversationService(conversationRepository, userRepository, restTemplate);
    }

    @Test
    void testSaveConversation() {
        String username = "testUser";
        String message = "Hello";
        String quote = "Test quote";
        User user = new User(username);
        when(userRepository.findByName(username)).thenReturn(user);

        conversationService.saveConversation(username, message, quote);

        verify(conversationRepository, times(1)).save(any(Conversation.class));
    }

    @Test
    void testGetConversationsByUser() {
        Integer userId = 1;
        List<Conversation> expectedConversations = Arrays.asList(new Conversation(), new Conversation());
        when(conversationRepository.findByUserId(userId)).thenReturn(expectedConversations);

        List<Conversation> result = conversationService.getConversationsByUser(userId);

        assertEquals(expectedConversations, result);
    }

    @Test
    void testGetAllUsers() {
        List<User> expectedUsers = Arrays.asList(new User("user1"), new User("user2"));
        when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(expectedUsers);

        List<User> result = conversationService.getAllUsers();

        assertEquals(expectedUsers, result);
    }

    @Test
    void testGetQuote_Success() {
        String expectedQuote = "Life is beautiful";
        when(restTemplate.getForObject(anyString(), eq(ConversationService.QuoteResponse.class)))
            .thenReturn(new ConversationService.QuoteResponse(expectedQuote));

        String result = conversationService.getQuote();

        assertEquals(expectedQuote, result);
    }

    @Test
    void testGetQuote_Error() {
        when(restTemplate.getForObject(anyString(), eq(ConversationService.QuoteResponse.class)))
            .thenThrow(new RuntimeException("API Error"));

        String result = conversationService.getQuote();

        assertEquals("Désolé, un problème technique est survenu.", result);
    }
}
