/**
 * 
 */
package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Notification;

/**
 * @author Shubham
 *
 */
public interface NotificationDaoInterface {

	/**
	 * Save Notification to the database
	 * @param sid Student ID
	 * @param type Mode of payment
	 * @param message Message to be displayed
	 */
	public void saveNotification(String sid, String type, String message);

	/**
	 * Method to show notification of the student
	 * @param sid student ID
	 */
	public List<Notification> getNotifications(String sid);

	/**
	 * To generate unique notification ID
	 * @return
	 */
	public int generateNotificationId();
	
}
