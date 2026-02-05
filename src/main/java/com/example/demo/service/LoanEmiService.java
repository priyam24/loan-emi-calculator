package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class LoanEmiService {

    public double calculateEmi(double principal,
                               double annualInterestRate,
                               int tenureInMonths) {

        double monthlyRate = (annualInterestRate / 100) / 12;
        double numerator = principal * monthlyRate * Math.pow(1 + monthlyRate, tenureInMonths);
        double denominator = Math.pow(1 + monthlyRate, tenureInMonths) - 1;

        return Math.round(numerator / denominator);
    }
}