package com.example.restservice.controller;

import com.example.restservice.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

import java.util.List;

@RestController("/loans")
public class LoanController {

	private ILoanService loanService;

	@Autowired
	public LoanController(ILoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("/loans")
	public List<Loan> getLoans() {
		return loanService.getLoans();
	}

	@GetMapping("/loans/{loanId}")
	public Loan getLoan(@PathVariable Long loanId) {
		return loanService.getLoan(loanId);
	}

	@GetMapping("/loans/calculate/{loanId}")
	public LoanMetric calculateLoanMetric(@PathVariable Long loanId) {
		return loanService.calculateLoanMetric(loanId);
	}

	public LoanMetric calculateLoanMetric(Loan loan) {
		return loanService.calculateLoanMetric(loan);
	}

	@GetMapping("/loans/maxPayment")
	public Loan getMaxMonthlyPaymentLoan() {
		return loanService.getMaxMonthlyPaymentLoan();
	}

}
