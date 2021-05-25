/**
 * 
 */
package com.flipkart.bean;

import java.util.Random;

import com.flipkart.constant.ModeOfPayment;

/**
 * @author Shubham
 *
 */
public class Payment {
	int paymentId;
	String studentID;
	ModeOfPayment paymentMethod;
	float amount;
	boolean status;
	
	public Payment(String studentID, ModeOfPayment mode, float amount) {
		this.studentID = studentID;
		this.paymentMethod = mode;
		this.amount = amount;
		Random rand = new Random();
		paymentId = rand.nextInt();
		this.status = true;
	}
	
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
	
	public ModeOfPayment getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(ModeOfPayment paymentMethod) {
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
