package com.example.PawsitiveChat.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PawsitiveChat.model.Conversation;
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
    public String handleConversationSubmission(@RequestParam("userName") String userName,
                                               @RequestParam("message") String message,
                                               Model model) {
        conversationService.saveConversation(userName, message);
        return "conversation";
    }

    @GetMapping("/conversations")
    public String listConversations(Model model) {
        List<Conversation> conversations = conversationService.getAllConversations();
        model.addAttribute("conversations", conversations);
        return "conversations";
    }
}
