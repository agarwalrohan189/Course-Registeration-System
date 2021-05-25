package com.flipkart.service;

import com.flipkart.bean.Payment;

public class NotificationOperation implements NotificationInterface{

    @Override
    public int sendNotification(String notificationMessage, String studentID, Payment method, double amount) {
        System.out.println("______________________");
    	System.out.println("Result of Payment:");
        System.out.println(notificationMessage);
        System.out.println("______________________");
    	return 0;
    }
}
