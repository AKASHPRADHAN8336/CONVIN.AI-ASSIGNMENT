package com.example.convinai.repository;

import com.example.convinai.entity.ExpenseSplit;
import com.example.convinai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, Long> {
    List<ExpenseSplit> findAllByUser(User user);

}
