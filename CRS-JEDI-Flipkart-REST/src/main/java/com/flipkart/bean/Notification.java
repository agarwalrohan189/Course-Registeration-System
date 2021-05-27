/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Shubham
 *
 */
public class Notification {

	private int notificationId;
	private String studentId, type, message;
	
	/**
	 * @param notificationId
	 * @param studentId
	 * @param type
	 * @param message
	 */
	public Notification(int notificationId, String studentId, String type, String message) {
		this.notificationId = notificationId;
		this.studentId = studentId;
		this.type = type;
		this.message = message;
	}
	
	/**
	 * @return the notificationId
	 */
	public int getNotificationId() {
		return notificationId;
	}
	/**
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
