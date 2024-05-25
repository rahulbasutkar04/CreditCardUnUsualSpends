package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.service.EmailService;
import com.amaap.creditcardunusualspends.dto.UnusualSpendAlertDTO;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;

import java.util.Map;

public class NotificationService {
    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;
    private final ExpenditureRepository expenditureRepository;
    private final EmailService emailService;



    public NotificationService(UserRepository userRepository, CreditCardRepository creditCardRepository, ExpenditureRepository expenditureRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
        this.expenditureRepository = expenditureRepository;
        this.emailService = emailService;
    }

    public void notifyUnusualSpending(int userId) {
        String userName = userRepository.getUserNameById(userId);
        String userEmail = userRepository.getUserEmailById(userId);
        long ccNumber = creditCardRepository.getCreditCardNumberByUserId(userId);

        Map<String, Double> unusualSpends = expenditureRepository.getUnusualSpendData(ccNumber);
        if (unusualSpends.isEmpty()) {
            return;
        }

        double totalUnusualSpends = unusualSpends.values().stream().mapToDouble(Double::doubleValue).sum();

        UnusualSpendAlertDTO alertDTO = new UnusualSpendAlertDTO(userName, userEmail, unusualSpends, totalUnusualSpends);

        emailService.sendUnusualSpendAlert(alertDTO);
    }
}
