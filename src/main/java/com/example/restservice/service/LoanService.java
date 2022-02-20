package com.example.restservice.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.restservice.metrics.ILoanMetricFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;

@Service
public class LoanService implements ILoanService{

	private ILoanMetricFactory loanMetricFactory;
	/**
	 * To simulate a persistence, I save the Loans list into the service object
	 */
	private List<Loan> loans;

	@Autowired
	public LoanService(ILoanMetricFactory loanMetricFactory){
		this.loanMetricFactory = loanMetricFactory;
		this.loans = LoanGeneratonUtil.getRandomLoans(30L);
	}

	/**
	 * On this method I used a stream to get the Loan, based on the id
	 * @param id
	 * @return
	 */
	public Loan getLoan(Long id) {
		return loans.stream().filter(loan -> loan.getLoanId().equals(id)).findAny().orElse(null);
	}

	/**
	 * I added this method to the interface, to simplify the validations
	 * @return
	 */
	public List<Loan> getLoans(){
		return loans;
	}

	public LoanMetric calculateLoanMetric(Loan loan) {
		// Use the LoanMetricFactory based on the loan type
		return loanMetricFactory.getInstance(loan).getLoanMetric(loan);
	}

	public LoanMetric calculateLoanMetric(Long loanId) {
		// Call getLoan(loanId)
		Loan loan = getLoan(loanId);
		return loanMetricFactory.getInstance(loan).getLoanMetric(loan);
	}

	public Loan getMaxMonthlyPaymentLoan() {
		// get the loan with the max monthly payment
		/** First I process the entire list into a map, where the Key is the original Loan,
		 *		and the value is its respective LoanMetric Object calculated
		 */
		Map<Loan, LoanMetric> loanMap =  loans.stream()
			.collect(
				Collectors.toMap(
						Function.identity(),
						(loan) -> loanMetricFactory.getInstance(loan).getLoanMetric(loan)
					)
			);
		/**
		 * then I search the max MontlyPayment from the map, and returned the respective Loan (the Key on the Map)
		 */
		return loanMap.entrySet().stream().max(Comparator.comparing(e -> e.getValue().getMonthlyPayment())).get().getKey();
	}
}
