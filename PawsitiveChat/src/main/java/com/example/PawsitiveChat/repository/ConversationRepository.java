package com.example.PawsitiveChat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PawsitiveChat.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByUserId(Integer userId);
}
