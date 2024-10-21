package com.example.convinai.dto;

import com.example.convinai.entity.SplitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExpenseSplitDTO {
    private String email;
    private SplitType splitType;
    private Double amount;
    private Double percentage;
    // Getters and setters
}
