package com.example.PawsitiveAPI.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.PawsitiveAPI.model.Quote;
import com.example.PawsitiveAPI.repository.QuoteRepository;

class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quoteService = new QuoteService(quoteRepository);
    }

    @Test
    void testGetRandomQuote_WithQuotes() {
        List<Quote> quotes = Arrays.asList(
            new Quote("Ne sois pas déprimé."),
            new Quote("Tout va bien."),
            new Quote("La vie est belle.")
        );
        when(quoteRepository.findAll()).thenReturn(quotes);

        Quote result = quoteService.getRandomQuote();

        assertNotNull(result, "The result should not be null");
        assertTrue(quotes.contains(result), "The result should be one of the quotes from the list");
        verify(quoteRepository, times(1)).findAll();
    }

    @Test
    void testGetRandomQuote_EmptyList() {
        when(quoteRepository.findAll()).thenReturn(Arrays.asList());

        Quote result = quoteService.getRandomQuote();

        assertNull(result, "The result should be null when the list is empty");
        verify(quoteRepository, times(1)).findAll();
    }
}
