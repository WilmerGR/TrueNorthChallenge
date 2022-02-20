package com.example.restservice.service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

import java.util.List;

public interface ILoanService {

    Loan getLoan(Long id);

    List<Loan> getLoans();

    LoanMetric calculateLoanMetric(Loan loan);

    LoanMetric calculateLoanMetric(Long loanId);

    Loan getMaxMonthlyPaymentLoan();

}
