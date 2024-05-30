package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.service.UnusualSpendAnalyser;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class ExpenditureService {

    private final TransactionRepository transactionRepository;
    private final CreditCardRepository creditCardRepository;
    private final ExpenditureRepository expenditureRepository;
    UnusualSpendAnalyser unusualSpendAnalyser=new UnusualSpendAnalyser();

    @Inject
    public ExpenditureService(TransactionRepository transactionRepository, CreditCardRepository creditCardRepository,ExpenditureRepository expenditureRepository) {
        this.transactionRepository = transactionRepository;
        this.creditCardRepository = creditCardRepository;
        this.expenditureRepository=expenditureRepository;
    }

    public boolean getUnusualSpend(long creditCardNumber) throws InvaliCreditCardNumberException {
        if(!creditCardRepository.isCreditCardPresent(creditCardNumber)) throw  new InvaliCreditCardNumberException("No Credit Card Found"+creditCardNumber);
        List<Map<String, Object>> spendData= unusualSpendAnalyser.getSpends(transactionRepository.getTransactionData());

        expenditureRepository.addIntoExpenditureData(spendData);


        return  expenditureRepository.getSpendData().size()!=0;
    }
}
