/**
 * 
 */
package com.flipkart.dao;

/**
 * @author Shubham
 *
 */
public interface NotificationDaoInterface {

	public void saveNotification(String sid, String type, String message);
	
	public void showNotifications(String sid);
	
	public int generateNotificationId();
	
}
