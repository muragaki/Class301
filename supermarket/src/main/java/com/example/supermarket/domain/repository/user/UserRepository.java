package com.example.supermarket.domain.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermarket.domain.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
