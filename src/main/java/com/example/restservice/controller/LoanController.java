package com.example.restservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@RestController("/loans")
public class LoanController {

	public Loan getLoan(Long loanId) {
		return null;
	}

	public LoanMetric calculateLoanMetric(Long loanId) {
		// Call LoanService
		return null;
	}

	public LoanMetric calculateLoanMetric(Loan loan) {
		// Call LoanService
		return null;
	}

	public Loan getMaxMonthlyPaymentLoan() {
		// Call LoanService
		return null;
	}

}
