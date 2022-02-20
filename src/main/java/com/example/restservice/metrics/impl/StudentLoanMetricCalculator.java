package com.example.restservice.metrics.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component
@Qualifier("StudentCalculator")
public class StudentLoanMetricCalculator implements ILoanMetricCalculator {

	@Override
	public boolean isSupported(Loan loan) {
		if(loan.getBorrower().getAge() > 18 && loan.getBorrower().getAge() < 30){
			return true;
		}
		return false;
	}

	@Override
	public LoanMetric getLoanMetric(Loan loan) {

		double monthlyInterestRate = calculateMonthlyInterestRate(loan);
		double monthlyPayment = calculateMonthlyPayment(loan, monthlyInterestRate);

		return new LoanMetric(monthlyInterestRate, monthlyPayment);
	}

	/**
		monthlyInterestRate = (annualInterest /12 ) / 100
	 */
	private double calculateMonthlyInterestRate(Loan loan){
		return loan.getAnnualInterest() / 1200;
	}

	/**
		monthlyPayment = 0.8 * (requestedAmount * monthlyInterestRate) / (1 - (1 +
		monthlyInterestRate)^((-1) * termMonths) )
	 */
	private double calculateMonthlyPayment(Loan loan, double monthlyInterestRate) {
		return 0.8 * (loan.getRequestedAmount() * monthlyInterestRate) /
				(1 - Math.pow(1 + monthlyInterestRate, (-1) * loan.getTermMonths() ) );
	}

}
