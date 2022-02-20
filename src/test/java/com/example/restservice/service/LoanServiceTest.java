package com.example.restservice.service;


import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.ILoanMetricFactory;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import com.example.restservice.model.Loan;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.restservice.model.LoanMetric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;


public class LoanServiceTest {

    ILoanService loanService;

    @Mock
    ILoanMetricFactory loanMetricFactory;

    @Mock
    ILoanMetricCalculator loanMetricCalculator;

    @BeforeEach
    public void init(){
        loanMetricFactory = mock(LoanMetricFactory.class);
        loanMetricCalculator = mock(StudentLoanMetricCalculator.class);
        when(loanMetricFactory.getInstance(any())).thenReturn(loanMetricCalculator);

        loanService = new LoanService(loanMetricFactory);
    }

    @Test
    public void validateLoansListSize(){
        List<Loan> loans = loanService.getLoans();

        assertEquals(30, loans.size());
    }

    @Test
    public void validateSpecificLoan(){
        Loan loan = loanService.getLoan(2l);

        assertEquals(2, loan.getLoanId());
    }

    @Test
    public void getLoanMetricId(){

        when(loanMetricCalculator.getLoanMetric(any())).thenReturn(new LoanMetric(10.0, 9.0));

        LoanMetric loanMetric = loanService.calculateLoanMetric(1l);

        assertEquals(9.0, loanMetric.getMonthlyPayment());
    }

    @Test
    public void getLoanMetric(){

        when(loanMetricCalculator.getLoanMetric(any())).thenReturn(new LoanMetric(10.0, 9.0));

        LoanMetric loanMetric = loanService.calculateLoanMetric(new Loan());

        assertEquals(9.0, loanMetric.getMonthlyPayment());
    }

    @Test
    public void validateMaxPayment(){

        ILoanMetricCalculator consumerLoanMetricCalculator = new ConsumerLoanMetricCalculator();
        ILoanMetricCalculator studentLoanMetricCalculator = new StudentLoanMetricCalculator();

        loanMetricFactory = new LoanMetricFactory(consumerLoanMetricCalculator, studentLoanMetricCalculator);

        loanService = new LoanService(loanMetricFactory);

        Loan loan = loanService.getMaxMonthlyPaymentLoan();

        assertEquals(28, loan.getLoanId());
    }
}
