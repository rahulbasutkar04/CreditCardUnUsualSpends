package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.Categories;
import com.amaap.creditcardunusualspends.domain.model.entity.Transaction;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCategoryException;
import com.amaap.creditcardunusualspends.service.exception.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionService {

    TransactionRepository transactionRepository;
    CreditCardRepository creditCardRepository;

    public TransactionService(TransactionRepository transactionRepository,CreditCardRepository creditCardRepository) {
        this.transactionRepository = transactionRepository;
        this.creditCardRepository=creditCardRepository;
    }

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public boolean performTransaction(long creditCardNumber, String date, String category, long amount) throws InvalidDateException, InvalidCategoryException, InvaliCreditCardNumberException {
        if(date.isEmpty()) throw  new InvalidDateException("Invalid Date :"+date);
        if(category.isEmpty()) throw new InvalidCategoryException("Invalid Category:"+category);
        if(!creditCardRepository.isCreditCardPresent(creditCardNumber)) throw  new InvaliCreditCardNumberException("No Credit Card Found"+creditCardNumber);

        try {
            Date newDate= DATE_FORMAT.parse(date);
            Categories newCategory = Categories.valueOf(category.toUpperCase());

            Transaction transaction=new Transaction(creditCardNumber,newDate,newCategory,amount);
            transactionRepository.addIntoTransactionData(transaction);
            System.out.println("Transaction Performed..");
            return transactionRepository.getTransactionData().size()!=0;
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please use dd-MM-yyyy.");
            return false;
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid category. Please use a valid category.");
            return false;
        }
    }


}
