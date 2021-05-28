package com.flipkart.service;

import java.util.List;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;

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
//    public int sendNotification(String notificationMessage, String studentID, Payment method, double amount);

	/**
     * Method for sending notification to the student.
     * @param sid -> ID of student to whom notification is to be sent
     * @param type -> type of notification
     * @param message -> notification message
     */
	public void sendNotification(String sid, String type, String message);
	
	/**
     * Method for viewing notification for the student.
     * @param sid -> ID of student to whom notification is to be sent
     * @return List<Notification> -> list of notification objects containing notification details
     */
	public List<Notification> getNotifications(String sid);
}
