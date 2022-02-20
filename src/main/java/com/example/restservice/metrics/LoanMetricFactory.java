package com.example.restservice.metrics;

import com.example.restservice.util.LoanGeneratonUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

@Service
public class LoanMetricFactory implements ILoanMetricFactory {

   private ILoanMetricCalculator consumerLoanMetricCalculator;
   private ILoanMetricCalculator studentLoanMetricCalculator;

   public LoanMetricFactory(@Qualifier("ConsumerCalculator") ILoanMetricCalculator consumerLoanMetricCalculator,
                            @Qualifier("StudentCalculator") ILoanMetricCalculator studentLoanMetricCalculator){

      this.consumerLoanMetricCalculator = consumerLoanMetricCalculator;
      this.studentLoanMetricCalculator = studentLoanMetricCalculator;

   }
   
   @Override
   public ILoanMetricCalculator getInstance(Loan loan) {
      if(loan.getType().equals(LoanGeneratonUtil.LOAN_TYPE_STUDENT)){
         return studentLoanMetricCalculator;
      }
      return consumerLoanMetricCalculator;
   }

}
