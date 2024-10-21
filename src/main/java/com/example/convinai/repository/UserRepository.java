package com.example.convinai.repository;

import com.example.convinai.entity.Expense;
import com.example.convinai.entity.ExpenseSplit;
import com.example.convinai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}


