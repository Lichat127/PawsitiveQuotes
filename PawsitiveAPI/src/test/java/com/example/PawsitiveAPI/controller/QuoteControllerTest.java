package com.example.PawsitiveAPI.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.PawsitiveAPI.model.Quote;
import com.example.PawsitiveAPI.service.QuoteService;

class QuoteControllerTest {

    @Mock
    private QuoteService quoteService;

    private QuoteController quoteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quoteController = new QuoteController(quoteService);
    }

    @Test
    void testApiHome() {
        ResponseEntity<?> response = quoteController.apiHome();

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK");
        assertEquals("Bienvenue sur l'API Pawsitive. Utilisez /api/quote pour obtenir une citation aléatoire.", response.getBody(), "Welcome message is incorrect");
    }

    @Test
    void testGetQuote_Success() {
        Quote mockQuote = new Quote("Test quote");
        when(quoteService.getRandomQuote()).thenReturn(mockQuote);

        ResponseEntity<?> response = quoteController.getQuote();

        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status should be OK");
        assertEquals(mockQuote, response.getBody(), "Returned quote is incorrect");
    }

    @Test
    void testGetQuote_NotFound() {
        when(quoteService.getRandomQuote()).thenReturn(null);

        ResponseEntity<?> response = quoteController.getQuote();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Status should be NOT_FOUND");
        assertEquals("Aucune citation trouvée dans la base de donnée", response.getBody(), "Error message is incorrect");
    }

    @Test
    void testGetQuote_InternalServerError() {
        when(quoteService.getRandomQuote()).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<?> response = quoteController.getQuote();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Status should be INTERNAL_SERVER_ERROR");
        assertEquals("Une erreur est survenue lors de la récupération de la citation.", response.getBody(), "Error message is incorrect");
    }
}
