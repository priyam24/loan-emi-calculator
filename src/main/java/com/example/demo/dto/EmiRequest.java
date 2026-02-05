package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EmiRequest {

    @NotNull
    @Positive
    private Double loanAmount;

    @NotNull
    @Min(0)
    @Max(100)
    private Double annualInterestRate;

    @NotNull
    @Min(1)
    @Max(30)
    private Integer loanTermInMonths;

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(Double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public Integer getLoanTermInMonths() {
		return loanTermInMonths;
	}

	public void setLoanTermInMonths(Integer loanTermInMonths) {
		this.loanTermInMonths = loanTermInMonths;
	}
}
