/**
 * 
 */
package com.flipkart.bean;

/**
 * @author rohanagarwal
 *
 */
public class PaymentNotification {
	
	private int notificationId;
	private int paymentId;
	private float amount;
	private String paymentMethod;
	private String notificationMessage;
	
	public PaymentNotification(int notificationId, int paymentId, float amount, String paymentMethod,
			String notificationMessage) {
		this.notificationId = notificationId;
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.notificationMessage = notificationMessage;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	
	
	
}
