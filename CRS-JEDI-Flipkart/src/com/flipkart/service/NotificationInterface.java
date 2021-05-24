package com.flipkart.service;

import com.flipkart.bean.Payment;

/**
 * Below interface describes operation of sending notification.
 */
public interface NotificationInterface {

    /**
     * Method for sending notification to the student.
     * @param notificationMessage -> Message of notification
     * @param studentID -> ID of student to whom notification is to be sent
     * @param method -> Method of payment
     * @param amount -> Amount of payment
     * @return -> notification ID
     */
    public int sendNotification(String notificationMessage, String studentID, Payment method, double amount);
}