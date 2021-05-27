/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Notification;
import com.flipkart.client.LoginMenu;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;

/**
 * @author Shubham
 *
 */
public class NotificationDaoOperation implements NotificationDaoInterface{
	private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);

	private static volatile NotificationDaoOperation instance = null;

	/**
	 * To get instance of the class with singleton pattern
	 * @return
	 */
	public static NotificationDaoOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(NotificationDaoOperation.class){
				instance=new NotificationDaoOperation();
			}
		}
		return instance;
	}

	/**
	 * Save Notification to the database
	 * @param sid Student ID
	 * @param type Mode of payment
	 * @param message Message to be displayed
	 */
	@Override
	public void saveNotification(String sid, String type, String message) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		try
		{
			PreparedStatement insertNotification = conn.prepareStatement(SQLQueries.INSERT_NOTIFICATION);
			insertNotification.setString(1, sid);
			int nid = generateNotificationId();
			if (nid == -1)
				return;
			insertNotification.setInt(2, nid);
			insertNotification.setString(3, type);
			insertNotification.setString(4, message);
			insertNotification.executeUpdate();
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method to show notification of the student
	 * @param sid student ID
	 */
	@Override
	public List<Notification> getNotifications(String sid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		try
		{
			PreparedStatement selectNotification = conn.prepareStatement(SQLQueries.SELECT_NOTIFICATION);
			selectNotification.setString(1, sid);
			ResultSet result = selectNotification.executeQuery();
			
			List<Notification> notificationsList = new ArrayList<Notification>();
			
			while (result.next())
			{
				Notification n = new Notification (result.getInt("nid"), result.getString("sid"), result.getString("notificationType"), result.getString("message"));
				notificationsList.add(n);
			}
			return notificationsList;
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * To generate unique notification ID
	 * @return
	 */
	public int generateNotificationId()
	{
		Connection conn = DBUtil.getConnection();
		int notificationId = 0;
		try
		{
			PreparedStatement getAllNotifications = conn.prepareStatement(SQLQueries.SELECT_ALL_NOTIFICATIONS);
			ResultSet result = getAllNotifications.executeQuery();
			int maxNotificationId = 0;
			
			while (result.next())
			{
				int nid = result.getInt("nid");
				if (nid > maxNotificationId)
					maxNotificationId = nid;
			}
			
			notificationId = maxNotificationId+1;
			return notificationId;
		}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return notificationId;
	}

}
