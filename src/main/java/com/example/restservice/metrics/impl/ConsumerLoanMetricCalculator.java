package com.example.restservice.metrics.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component
@Qualifier("ConsumerCalculator")
public class ConsumerLoanMetricCalculator implements ILoanMetricCalculator {

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
		return  (loan.getRequestedAmount() * monthlyInterestRate) /
				(1 - Math.pow(1 + monthlyInterestRate, (-1) * loan.getTermMonths() ) );
	}

}
