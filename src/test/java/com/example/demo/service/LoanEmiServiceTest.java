package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LoanEmiServiceTest {
	
	private final LoanEmiService service = new LoanEmiService();
	
    @Test
    void shouldCalculateEmiSuccessfully() {
        double emi = service.calculateEmi(100000, 12, 12);
        assertEquals(8885.0, emi);
    }
}
