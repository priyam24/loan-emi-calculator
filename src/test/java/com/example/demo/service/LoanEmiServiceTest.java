package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanEmiServiceTest {
	
    private LoanEmiService emiService;

    @BeforeEach
    void setUp() {
        emiService = new LoanEmiService();
    }

    @Test
    void testCalculateEmi_normalCase() {
        double emi = emiService.calculateEmi(500000, 8.5, 240);
        assertEquals(4339.12, emi, 0.01); // small delta for floating point
    }

    @Test
    void testCalculateEmi_zeroInterest() {
        double emi = emiService.calculateEmi(120000, 0.0, 12);
        assertEquals(10000.00, emi, 0.01);
    }

    @Test
    void testCalculateEmi_largeTenure() {
        double emi = emiService.calculateEmi(1000000, 7.5, 600); // 50 years
        assertTrue(emi > 0);
    }

    @Test
    void testCalculateEmi_zeroPrincipal_throwsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                emiService.calculateEmi(0, 8.5, 120)
        );
        assertEquals("Principal must be greater than 0", ex.getMessage());
    }

    @Test
    void testCalculateEmi_negativePrincipal_throwsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                emiService.calculateEmi(-50000, 8.5, 120)
        );
        assertEquals("Principal must be greater than 0", ex.getMessage());
    }

    @Test
    void testCalculateEmi_zeroTenure_throwsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                emiService.calculateEmi(500000, 8.5, 0)
        );
        assertEquals("Tenure must be greater than 0 months", ex.getMessage());
    }

    @Test
    void testCalculateEmi_negativeTenure_throwsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                emiService.calculateEmi(500000, 8.5, -12)
        );
        assertEquals("Tenure must be greater than 0 months", ex.getMessage());
    }

    @Test
    void testCalculateEmi_negativeInterest_throwsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                emiService.calculateEmi(500000, -5.0, 120)
        );
        assertEquals("Interest rate cannot be negative", ex.getMessage());
    }
}
