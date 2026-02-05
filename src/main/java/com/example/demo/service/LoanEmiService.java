package com.example.demo.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class LoanEmiService {

    public double calculateEmi(double principal,
                               double annualInterestRate,
                               int tenureInMonths) {
    	
        basicValidation(principal, annualInterestRate, tenureInMonths);

        MathContext mc = new MathContext(15, RoundingMode.HALF_UP);

        BigDecimal principalBD = BigDecimal.valueOf(principal);
        BigDecimal annualRateBD = BigDecimal.valueOf(annualInterestRate);
        
        //in case of zero interest
        if (annualInterestRate == 0.0) {
            BigDecimal emi = principalBD
                    .divide(BigDecimal.valueOf(tenureInMonths), 2, RoundingMode.HALF_UP);
            return emi.doubleValue();
        }

        // monthlyRate = (annualInterestRate / 100) / 12
        BigDecimal monthlyRate = annualRateBD
                .divide(BigDecimal.valueOf(100), mc)
                .divide(BigDecimal.valueOf(12), mc);

        // (1 + R)^N
        BigDecimal onePlusRPowerN = BigDecimal.ONE
                .add(monthlyRate)
                .pow(tenureInMonths, mc);

        // numerator = P * R * (1 + R)^N
        BigDecimal numerator = principalBD
                .multiply(monthlyRate, mc)
                .multiply(onePlusRPowerN, mc);

        // denominator = (1 + R)^N - 1
        BigDecimal denominator = onePlusRPowerN.subtract(BigDecimal.ONE, mc);

        // EMI = numerator / denominator (rounded to 2 decimals)
        BigDecimal emi = numerator.divide(denominator, 2, RoundingMode.HALF_UP);

        return emi.doubleValue();
    }

	private void basicValidation(double principal, double annualInterestRate, int tenureInMonths) {
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal must be greater than 0");
        }
        if (tenureInMonths <= 0) {
            throw new IllegalArgumentException("Tenure must be greater than 0 months");
        }
        if (annualInterestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
	}
}