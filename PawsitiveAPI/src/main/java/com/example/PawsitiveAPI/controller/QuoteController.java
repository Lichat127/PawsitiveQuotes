package com.example.PawsitiveAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getQuote() {
    	try {
    		Quote quote = quoteService.getRandomQuote();
    		if (quote == null) {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune citation trouvée dans la base de donnée");
    		}
    		return ResponseEntity.ok(quote);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la récupération de la citation.");
    	}
    }
}
