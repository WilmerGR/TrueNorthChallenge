package com.example.restservice.metrics;

import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentMetricCalculatorTest {

    StudentLoanMetricCalculator studentLoanMetricCalculator;

    @BeforeEach
    public void init(){
        studentLoanMetricCalculator = new StudentLoanMetricCalculator();
    }

    @Test
    public void validateSupported(){
        Loan loan = new Loan();
        Borrower borrower = new Borrower();
        borrower.setAge(19);

        loan.setBorrower(borrower);

        boolean result = studentLoanMetricCalculator.isSupported(loan);

        assertTrue(result);
    }

    @Test
    public void validateNotSupportedYoung(){
        Loan loan = new Loan();
        Borrower borrower = new Borrower();
        borrower.setAge(10);

        loan.setBorrower(borrower);

        boolean result = studentLoanMetricCalculator.isSupported(loan);

        assertFalse(result);
    }

    @Test
    public void validateNotSupportedOld(){
        Loan loan = new Loan();
        Borrower borrower = new Borrower();
        borrower.setAge(40);

        loan.setBorrower(borrower);

        boolean result = studentLoanMetricCalculator.isSupported(loan);

        assertFalse(result);
    }
}
