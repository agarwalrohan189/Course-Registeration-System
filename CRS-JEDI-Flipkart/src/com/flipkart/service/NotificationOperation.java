package com.flipkart.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;
import com.flipkart.bean.PaymentNotification;
import com.flipkart.client.CRSApplication;
import com.flipkart.dao.NotificationDaoOperation;

public class NotificationOperation implements NotificationInterface{
	private static Logger logger = Logger.getLogger(NotificationOperation.class);

	private static volatile NotificationOperation instance = null;

	private NotificationOperation(){}
	
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
//        logger.info("______________________");
//    	logger.info("Result of Payment:");
//        logger.info(NotifObj.getNotificationMessage());
//        logger.info("______________________");
//    	return 0;
//    }
	
	  @Override
	  public void sendNotification(String sid, String type, String message) {
		  if(type.equals("payment")) {
		      logger.info("______________________");
		  	  logger.info("Result of "+type+": ");
		      logger.info(message);
		      logger.info("______________________");
		  }
	      NotificationDaoOperation.getInstance().saveNotification(sid, type, message);
	  	return;
	  }
	  
	  public List<Notification> getNotifications(String sid)
	  {
		  return NotificationDaoOperation.getInstance().getNotifications(sid);
	  }
}
