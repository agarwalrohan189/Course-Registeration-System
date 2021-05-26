package com.flipkart.service;

import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.dao.NotificationDaoOperation;

public class NotificationOperation implements NotificationInterface{
	
	private static volatile NotificationOperation instance = null;
	
	public static NotificationOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(NotificationOperation.class){
				instance=new NotificationOperation();
			}
		}
		return instance;
	}
	
//    @Override
//    public int sendNotification(PaymentNotification NotifObj) {
//        System.out.println("______________________");
//    	System.out.println("Result of Payment:");
//        System.out.println(NotifObj.getNotificationMessage());
//        System.out.println("______________________");
//    	return 0;
//    }
	
	  @Override
	  public void sendNotification(String sid, String type, String message) {
	      System.out.println("______________________");
	  	  System.out.println("Result of "+type+": ");
	      System.out.println(message);
	      System.out.println("______________________");
	      NotificationDaoOperation.getInstance().saveNotification(sid, type, message);
	  	return;
	  }
	  
	  public void showNotifications(String sid)
	  {
		  NotificationDaoOperation.getInstance().showNotifications(sid);;
	  }
}
