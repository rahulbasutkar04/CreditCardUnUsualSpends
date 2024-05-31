package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.service.dto.UnusualSpendNotificationDTO;

import java.util.List;

public interface NotifierService {
    boolean sendNotification(String name,String email, List<UnusualSpendNotificationDTO.SpendDetail> spendDetails,double totalExpenditure);
}
