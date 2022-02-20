package com.example.restservice.metrics;

import com.example.restservice.model.Loan;

public interface ILoanMetricFactory {
    ILoanMetricCalculator getInstance(Loan loan);
}
