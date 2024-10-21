package com.example.convinai.service;

import com.example.convinai.dto.ExpenseDTO;
import com.example.convinai.dto.ExpenseSplitDTO;
import com.example.convinai.entity.Expense;
import com.example.convinai.entity.ExpenseSplit;
import com.example.convinai.entity.User;
import com.example.convinai.exception.UserNotFoundException;
import com.example.convinai.repository.ExpenseRepository;
import com.example.convinai.repository.ExpenseSplitRepository;
import com.example.convinai.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static com.example.convinai.entity.SplitType.EXACT;
import static com.example.convinai.entity.SplitType.PERCENTAGE;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseService {

    private ExpenseRepository expenseRepository;


    private ExpenseSplitRepository expenseSplitRepository;


    private UserRepository userRepository;

    public Expense addExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();
        expense.setTotalAmount(expenseDTO.getTotalAmount());

        List<ExpenseSplit> splits = new ArrayList<>();
        for (ExpenseSplitDTO splitDTO : expenseDTO.getSplits()) {
            User user = userRepository.findByEmail(splitDTO.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + splitDTO.getEmail()));

            ExpenseSplit split = new ExpenseSplit();
            split.setUser(user);
            split.setSplitType(splitDTO.getSplitType());

            switch (splitDTO.getSplitType()) {
                case EQUAL:
                    split.setAmount(expenseDTO.getTotalAmount() / expenseDTO.getSplits().size());
                    break;
                case EXACT:
                    split.setAmount(splitDTO.getAmount());
                    break;
                case PERCENTAGE:
                    split.setPercentage(splitDTO.getPercentage());
                    split.setAmount(expenseDTO.getTotalAmount() * splitDTO.getPercentage() / 100);
                    break;
            }

            split.setExpense(expense);
            splits.add(split);
        }

        expense.setSplits(splits);
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<ExpenseSplit> getUserExpenses(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return expenseSplitRepository.findAllByUser(user);
    }

    public byte[] generateBalanceSheet() {

        return new byte[0];
    }
}
