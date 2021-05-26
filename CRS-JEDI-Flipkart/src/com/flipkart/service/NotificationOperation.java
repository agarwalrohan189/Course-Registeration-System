package com.flipkart.service;

import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;

public class NotificationOperation implements NotificationInterface{

    @Override
    public int sendNotification(PaymentNotification NotifObj) {
        System.out.println("______________________");
    	System.out.println("Result of Payment:");
        System.out.println(NotifObj.getNotificationMessage());
        System.out.println("______________________");
    	return 0;
    }
}
