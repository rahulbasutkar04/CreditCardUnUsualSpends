package com.amaap.creditcardunusualspends.controller;

import com.amaap.creditcardunusualspends.controller.dto.Http;
import com.amaap.creditcardunusualspends.controller.dto.Response;
import com.amaap.creditcardunusualspends.service.NotificationService;
import com.google.inject.Inject;

public class NotificationController {
    private final NotificationService notificationService;

    @Inject
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public Response notifyUnusualSpending(int userId) {
        notificationService.notifyUnusualSpending(userId);
        return new Response(Http.OK, "Notification sent successfully.");
    }
}
