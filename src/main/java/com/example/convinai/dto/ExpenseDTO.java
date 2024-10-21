package com.example.convinai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseDTO {
    private Double totalAmount;
    private List<ExpenseSplitDTO> splits;
    // Getters and setters
}
