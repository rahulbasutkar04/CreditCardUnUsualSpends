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
        int userId = creditCardRepository.getUserIdByCreditCardNumber(creditCardNumber);
        System.out.println(userId);
        Customer customer = customerRepository.getCustomerById(userId);
        String userName = customer.getName();
        System.out.println(userName);
        String userEmail = customer.getEmail();
        System.out.println(userEmail);
        List<Map<String, Object>> spendData = expenditureRepository.getSpendData();


        Map<String, Double> aggregatedSpendData = spendData.stream()
                .collect(Collectors.groupingBy(
                        data -> (String) data.getOrDefault("category", "Unknown"),
                        Collectors.summingDouble(data -> parseAmount(data.get("extraAmount")))
                ));

        double totalExpenditure = aggregatedSpendData.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        List<UnusualSpendNotificationDTO.SpendDetail> spendDetails = aggregatedSpendData.entrySet().stream()
                .map(entry -> new UnusualSpendNotificationDTO.SpendDetail(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new UnusualSpendNotificationDTO(userName, userEmail, spendDetails, totalExpenditure);
    }

    private double parseAmount(Object amountObj) {
        if (amountObj instanceof Double) {
            return (Double) amountObj;
        } else if (amountObj instanceof Integer) {
            return ((Integer) amountObj).doubleValue();
        } else if (amountObj instanceof String) {
            try {
                return Double.parseDouble((String) amountObj);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }
}
