package com.example.PawsitiveChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.PawsitiveChat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
