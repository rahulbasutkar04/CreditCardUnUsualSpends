package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.domain.model.entity.Customer;
import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.CustomerRepository;
import com.amaap.creditcardunusualspends.repository.ExpenditureRepository;
import com.amaap.creditcardunusualspends.service.dto.UnusualSpendNotificationDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NotificationBuilder {

    private CreditCardRepository creditCardRepository;
    private CustomerRepository customerRepository;
    private ExpenditureRepository expenditureRepository;

    public NotificationBuilder(CreditCardRepository creditCardRepository, CustomerRepository customerRepository, ExpenditureRepository expenditureRepository) {
        this.creditCardRepository = creditCardRepository;
        this.customerRepository = customerRepository;
        this.expenditureRepository = expenditureRepository;
    }

    public UnusualSpendNotificationDTO buildNotifierDataFor(long creditCardNumber) {
        int userId=creditCardRepository.getUserIdByCreditCardNumber(creditCardNumber);
        Customer customer=customerRepository.getCustomerById(userId);
        String userName= customer.getName();
        String userEmail=customer.getEmail();
        List<Map<String, Object>> spendData = expenditureRepository.getSpendData();

        Map<String, Double> aggregatedSpendData = spendData.stream()
                .collect(Collectors.groupingBy(
                        data -> (String) data.get("category"),
                        Collectors.summingDouble(data -> (Double) data.get("amount"))
                ));

        double totalExpenditure = aggregatedSpendData.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        List<UnusualSpendNotificationDTO.SpendDetail> spendDetails = aggregatedSpendData.entrySet().stream()
                .map(entry -> new UnusualSpendNotificationDTO.SpendDetail(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new UnusualSpendNotificationDTO(userName, userEmail, spendDetails, totalExpenditure);
    }
}
