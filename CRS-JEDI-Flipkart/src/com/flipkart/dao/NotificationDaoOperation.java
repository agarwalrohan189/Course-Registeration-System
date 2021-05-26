/**
 * 
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;

/**
 * @author Shubham
 *
 */
public class NotificationDaoOperation implements NotificationDaoInterface{

	private static volatile NotificationDaoOperation instance = null;
	
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
			System.err.println(e.getMessage());
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

	@Override
	public void showNotifications(String sid) {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		try
		{
			PreparedStatement selectNotification = conn.prepareStatement(SQLQueries.SELECT_NOTIFICATION);
			selectNotification.setString(1, sid);
			ResultSet result = selectNotification.executeQuery();
			
			System.out.println("Notifications for student with id: " + sid);
			
			while (result.next())
			{
				System.out.println("\nNotification id: " + result.getInt("nid"));
				System.out.println("Student id: " + result.getString("sid"));
				System.out.println("Notification type: " + result.getString("notificationType"));
				System.out.println("Notification message: " + result.getString("message")+"\n");
			}
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
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
			System.err.println(e.getMessage());
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
