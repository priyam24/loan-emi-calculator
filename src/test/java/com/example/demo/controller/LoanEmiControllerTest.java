package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.EmiRequest;
import com.example.demo.service.LoanEmiService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LoanEmiController.class)
class LoanEmiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanEmiService loanEmiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnEmi() throws Exception {
        EmiRequest request = new EmiRequest();
        request.setLoanAmount(100000.0);
        request.setAnnualInterestRate(12.0);
        request.setLoanTermInMonths(12);

        when(loanEmiService.calculateEmi(100000, 12, 12))
                .thenReturn(8884.0);

        mockMvc.perform(post("/api/loan/emi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emiAmount").value(8884.0));
    }
    
    @Test
    void shouldFailForNegativeLoanAmount() throws Exception {
        EmiRequest request = new EmiRequest();
        request.setLoanAmount(-100000.0);
        request.setAnnualInterestRate(12.0);
        request.setLoanTermInMonths(12);

        mockMvc.perform(post("/api/loan/emi")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
