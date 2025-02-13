package com.example.PawsitiveAPI.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.PawsitiveAPI.model.Quote;
import com.example.PawsitiveAPI.repository.QuoteRepository;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }
}
