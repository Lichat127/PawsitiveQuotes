package com.example.PawsitiveChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PawsitiveChat.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
