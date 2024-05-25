package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.service.UnusualSpendAnalyser;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;

public class ExpenditureService {
    private TransactionRepository transactionRepository;
    private CreditCardRepository creditCardRepository;
    private ExpenditureRepository expenditureRepository;
    private UnusualSpendAnalyser unusualSpendAnalyser;


    public ExpenditureService(TransactionRepository transactionRepository, CreditCardRepository creditCardRepository, ExpenditureRepository expenditureRepository, UnusualSpendAnalyser unusualSpendAnalyser) {
        this.transactionRepository = transactionRepository;
        this.creditCardRepository = creditCardRepository;
        this.expenditureRepository = expenditureRepository;
        this.unusualSpendAnalyser = unusualSpendAnalyser;
    }

    public boolean getSpends() {
        long ccNumber = creditCardRepository.GetCreditCardNumber();
        if (!unusualSpendAnalyser.calculateUnusualSpends(transactionRepository.getTransactionDataFor(ccNumber)).isEmpty()) {
            expenditureRepository.addUnUsualSpendData(unusualSpendAnalyser.calculateUnusualSpends(transactionRepository.getTransactionDataFor(ccNumber)));
            return true;
        }
        return false;

    }
}
