package com.example.PawsitiveAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PawsitiveAPI.model.Quote;
import com.example.PawsitiveAPI.service.QuoteService;

@RestController
@RequestMapping("/api")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quote")
    public Quote getQuote() {
        return quoteService.getRandomQuote();
    }
}
