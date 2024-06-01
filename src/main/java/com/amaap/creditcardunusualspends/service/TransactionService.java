package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.domain.model.valueobject.SpendCategory;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidAmountException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;
import com.google.inject.Inject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionService {

    TransactionRepository transactionRepository;
    CreditCardRepository creditCardRepository;


    @Inject
    public TransactionService(TransactionRepository transactionRepository, CreditCardRepository creditCardRepository) {
        this.transactionRepository = transactionRepository;
        this.creditCardRepository = creditCardRepository;
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public boolean performTransaction(long creditCardNumber, String date, String category, long amount) throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException, InvalidAmountException {


        if (date.isEmpty()) throw new InvalidDateException("Invalid Date :" + date);
        if (category.isEmpty()) throw new InvalidCategoryException("Invalid Category:" + category);
        if (amount <= 0) throw new InvalidAmountException("Invalid Amount :" + amount);
        if (!creditCardRepository.isCreditCardPresent(creditCardNumber))
            throw new InvaliCreditCardNumberException("No Credit Card Found" + creditCardNumber);

        try {
            Date newDate = DATE_FORMAT.parse(date);
            SpendCategory newCategory = SpendCategory.valueOf(category.toUpperCase());

            Transaction transaction = new Transaction(creditCardNumber, newDate, newCategory, amount);
            transactionRepository.addIntoTransactionData(transaction);
            System.out.println("Transaction Performed..");
            return transactionRepository.getTransactionData().size() != 0;
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please use dd-MM-yyyy.");
            return false;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid category. Please use a valid category.");
            return false;
        }
    }


}
