/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Shubham
 *
 */
public class Payment {
	int paymentId;
	String studentID, paymentMethod;
	float amount;
	boolean status;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
