package com.example.demo.dto;

public class EmiResponse {
	private Double emiAmount;
    public EmiResponse(Double emiAmount) {
    	this.emiAmount=emiAmount;
    }
	public Double getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}
    
}