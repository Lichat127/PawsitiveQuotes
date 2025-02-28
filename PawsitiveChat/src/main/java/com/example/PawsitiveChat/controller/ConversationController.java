package com.example.PawsitiveChat.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PawsitiveChat.model.Conversation;
import com.example.PawsitiveChat.model.User;
import com.example.PawsitiveChat.service.ConversationService;

@Controller
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/")
    public String conversationForm(Model model) {
        return "conversation";
    }

    @PostMapping("/submit")
    public String handleConversationSubmission(@RequestParam("username") String username,
                                               @RequestParam("message") String message,
                                               Model model) {
        String quote = conversationService.getQuote();
        conversationService.saveConversation(username, message, quote);
        model.addAttribute("quote", quote);
        return "conversation";
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = conversationService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("isEmpty", users.isEmpty());
        return "users";
    }

    @GetMapping("/conversations/{userId}")
    public String listConversationsByUser(@PathVariable Integer userId, Model model) {
        List<Conversation> conversations = conversationService.getConversationsByUser(userId);
        model.addAttribute("conversations", conversations);
        return "conversations";
    }
}
