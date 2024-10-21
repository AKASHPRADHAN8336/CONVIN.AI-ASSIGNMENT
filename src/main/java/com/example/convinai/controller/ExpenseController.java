package com.example.convinai.controller;

import com.example.convinai.dto.ExpenseDTO;
import com.example.convinai.entity.Expense;
import com.example.convinai.entity.ExpenseSplit;
import com.example.convinai.service.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;

    @PostMapping("/addExpenses")
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        Expense expense = expenseService.addExpense(expenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @GetMapping("/getAllExpenses")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/user_email/{email}")
    public ResponseEntity<List<ExpenseSplit>> getUserExpenses(@PathVariable String email) {
        List<ExpenseSplit> splits = expenseService.getUserExpenses(email);
        return ResponseEntity.ok(splits);
    }

    @GetMapping("/balanceSheet/download")
    public ResponseEntity<byte[]> downloadBalanceSheet() {
        byte[] balanceSheet = expenseService.generateBalanceSheet();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Akash_Kumar_Pradhan_java_kotlin_developer_resume.pdf")
                .body(balanceSheet);
    }
}

