package com.example.restservice.controller;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.service.ILoanService;
import com.example.restservice.service.LoanService;
import com.example.restservice.util.LoanGeneratonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

public class LoanControllerTests {

    @Mock
    ILoanService loanService;

    LoanController loanController;

    @BeforeEach
    public void init(){
        loanService = Mockito.mock(LoanService.class);
        loanController = new LoanController(loanService);
    }

    @Test
    public void getLoans(){
        Mockito.when(loanService.getLoans()).thenReturn(LoanGeneratonUtil.getRandomLoans(30l));

        List<Loan> loans = loanController.getLoans();

        Assertions.assertEquals(30, loans.size());
    }

    @Test
    public void getLoan(){
        Loan loan = new Loan();
        loan.setLoanId(1l);
        Mockito.when(loanService.getLoan(ArgumentMatchers.anyLong())).thenReturn(loan);

        Loan result = loanController.getLoan(1l);

        Assertions.assertEquals(1, result.getLoanId());
    }

    @Test
    public void calculateLoanMetric(){
        LoanMetric loanMetric = new LoanMetric(1.0, 300.0);

        Mockito.when(loanService.calculateLoanMetric(ArgumentMatchers.anyLong())).thenReturn(loanMetric);

        LoanMetric result = loanController.calculateLoanMetric(3l);

        Assertions.assertEquals(1.0, result.getMonthlyInterestRate());
    }

    @Test
    public void calculateLoanMetricObject(){
        LoanMetric loanMetric = new LoanMetric(1.4, 400.0);

        Mockito.when(loanService.calculateLoanMetric((Loan) ArgumentMatchers.any())).thenReturn(loanMetric);

        LoanMetric result = loanController.calculateLoanMetric(new Loan());

        Assertions.assertEquals(400.0, result.getMonthlyPayment());
    }

    @Test
    public void validateMaxPayment(){
        Loan loan = new Loan();
        loan.setLoanId(2l);

        Mockito.when(loanService.getMaxMonthlyPaymentLoan()).thenReturn(loan);

        Loan result = loanController.getMaxMonthlyPaymentLoan();

        Assertions.assertEquals(2, result.getLoanId());
    }

}
