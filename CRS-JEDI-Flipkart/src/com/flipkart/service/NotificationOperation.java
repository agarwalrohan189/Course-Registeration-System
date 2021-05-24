package com.flipkart.service;

import com.flipkart.bean.Payment;

public class NotificationOperation implements NotificationInterface{

    @Override
    public int sendNotification(String notificationMessage, String studentID, Payment method, double amount) {
        return 0;
    }
}
