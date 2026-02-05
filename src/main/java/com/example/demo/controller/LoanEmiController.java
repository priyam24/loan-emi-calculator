package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmiRequest;
import com.example.demo.dto.EmiResponse;
import com.example.demo.service.LoanEmiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loan")
public class LoanEmiController {

    private final LoanEmiService loanEmiService;
    
    public LoanEmiController(LoanEmiService loanEmiService) {
        this.loanEmiService = loanEmiService;
    }

    @PostMapping("/emi")
    public ResponseEntity<EmiResponse> calculateEmi(
            @Valid @RequestBody EmiRequest request) {

        double emi = loanEmiService.calculateEmi(
                request.getLoanAmount(),
                request.getAnnualInterestRate(),
                request.getLoanTermInMonths()
        );

        return ResponseEntity.ok(new EmiResponse(emi));
    }
}