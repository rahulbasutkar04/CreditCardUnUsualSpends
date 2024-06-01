package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.service.UnusualSpendAnalyser;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.TransactionRepository;
import com.amaap.creditcardunusualspends.service.dto.UnusualSpendNotificationDTO;
import com.amaap.creditcardunusualspends.service.exception.InvaliCreditCardNumberException;
import com.amaap.creditcardunusualspends.service.impl.EmailComposer;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

public class ExpenditureService {
    private final TransactionRepository transactionRepository;
    private final CreditCardRepository creditCardRepository;
    private final ExpenditureRepository expenditureRepository;
    private final UnusualSpendAnalyser unusualSpendAnalyser;
    private final CustomerRepository customerRepository;
    boolean IS_MAIL_SENT = false;

    @Inject
    public ExpenditureService(TransactionRepository transactionRepository, CreditCardRepository creditCardRepository, ExpenditureRepository expenditureRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.creditCardRepository = creditCardRepository;
        this.expenditureRepository = expenditureRepository;
        this.unusualSpendAnalyser = new UnusualSpendAnalyser();
        this.customerRepository = customerRepository;

    }


    public boolean getUnusualSpend(long creditCardNumber) throws InvaliCreditCardNumberException {
        if (!creditCardRepository.isCreditCardPresent(creditCardNumber))
            throw new InvaliCreditCardNumberException("No Credit Card Found " + creditCardNumber);

        List<Map<String, Object>> spendData = unusualSpendAnalyser.getSpends(transactionRepository.getTransactionData());

        expenditureRepository.addIntoExpenditureData(spendData);

        if (!expenditureRepository.getSpendData().isEmpty()) {
            System.out.println("Unusual Spend Detected...");
            NotificationBuilder notificationBuilder = new NotificationBuilder(creditCardRepository, customerRepository, expenditureRepository);
            UnusualSpendNotificationDTO unusualSpendNotificationDTO = notificationBuilder.buildNotifierDataFor(creditCardNumber);
            NotifierService notifierService = new EmailComposer();
            IS_MAIL_SENT = notifierService.sendNotification(unusualSpendNotificationDTO.getUserName(), unusualSpendNotificationDTO.getUserEmail(), unusualSpendNotificationDTO.getSpendDetails(), unusualSpendNotificationDTO.getTotalUnusualSpends());
        }
        return IS_MAIL_SENT;
    }

}
