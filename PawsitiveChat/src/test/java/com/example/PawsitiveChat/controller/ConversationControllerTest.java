package com.example.PawsitiveChat.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.PawsitiveChat.model.User;
import com.example.PawsitiveChat.service.ConversationService;

class ConversationControllerTest {

    private MockMvc mockMvc;
    private ConversationService conversationService;

    @BeforeEach
    void setUp() {
        conversationService = mock(ConversationService.class);
        ConversationController controller = new ConversationController(conversationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testConversationForm() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("conversation"));
    }

    @Test
    void testHandleConversationSubmission() throws Exception {
        when(conversationService.getQuote()).thenReturn("Test quote");

        mockMvc.perform(post("/submit")
               .param("username", "testUser")
               .param("message", "Hello"))
               .andExpect(status().isOk())
               .andExpect(view().name("conversation"))
               .andExpect(model().attribute("quote", "Test quote"));

        verify(conversationService).saveConversation(eq("testUser"), eq("Hello"), eq("Test quote"));
    }

    @Test
    void testListConversationsByUser() throws Exception {
        mockMvc.perform(get("/conversations/1"))
               .andExpect(status().isOk())
               .andExpect(view().name("conversations"))
               .andExpect(model().attributeExists("conversations"));

        verify(conversationService).getConversationsByUser(1);
    }
}
