package com.example.PawsitiveAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PawsitiveAPI.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
