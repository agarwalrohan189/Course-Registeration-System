/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.ModeOfPaymentConstant;

/**
 * @author rohanagarwal
 *
 */
public class PaymentNotification {
//	private static int staticNotificationId;
//	private int notificationId;
//	private int paymentId;
//	private float amount;
//	private ModeOfPayment paymentMethod;

	/**
	 * Message of notification
	 */
	private String message;
	
	/*public PaymentNotification(Payment payObj, float amtToBePaid) {
		if(!payObj.isStatus()) {
			message = "Payment Failed! Please Try again.";
		}else {
			message = "Amount(" + payObj.amount + ") has been fully paid via " + ModeOfPayment.getStringModeOfPayment(payObj.paymentMethod) + ". Thank You";
		}
		else if(payObj.amount > amtToBePaid) {
			message = "Amount(" + payObj.amount + ") paid via " + ModeOfPayment.getStringModeOfPayment(payObj.paymentMethod) + " is more than required(" + amtToBePaid + "). Please contact cash counter to get refund.";
		}else if(payObj.amount < amtToBePaid) {
			message = "Amount(" + payObj.amount + ") paid via " + ModeOfPayment.getStringModeOfPayment(payObj.paymentMethod) + " is less than required(" + amtToBePaid + "). Please deposit remaining fee before due date.";
		}
	}*/

	/**
	 * Getter of notification message
	 * @return -> Notification message
	 */
	public String getNotificationMessage() {
		return message;
	}

	/**
	 * Setter of notification message
	 * @param message -> New message
	 */
	public void setNotificationMessage(String message) {
		this.message = message;
	}

//	public int getNotificationId() {
//		return notificationId;
//	}
//
//	public void setNotificationId(int notificationId) {
//		this.notificationId = notificationId;
//	}
//
//	public int getPaymentId() {
//		return paymentId;
//	}
//
//	public void setPaymentId(int paymentId) {
//		this.paymentId = paymentId;
//	}
//
//	public float getAmount() {
//		return amount;
//	}
//
//	public void setAmount(float amount) {
//		this.amount = amount;
//	}
//
//	public ModeOfPayment getPaymentMethod() {
//		return paymentMethod;
//	}
//
//	public void setPaymentMethod(ModeOfPayment paymentMethod) {
//		this.paymentMethod = paymentMethod;
//	}
	
	
	
}
