/**
 * 
 */
package com.flipkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.flipkart.dao.NotificationDaoOperation;

/**
 * @author rohanagarwal
 *
 */
public class DBUtil {
	private static Logger logger = Logger.getLogger(DBUtil.class);

    /**
     * Get connection of SQL Database
     * @return -> Connection
     */
	@SuppressWarnings("unused")
	public static Connection getConnection() {
		
		Connection connection = null;
		
        if (connection != null) {
			return connection;
		} 
        else 
        {
            try 
            {
            	
            	Properties prop = new Properties();
//                InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("./config.properties");
            	InputStream inputStream = new FileInputStream("D:\\config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                
            }
            catch (ClassNotFoundException e){
                logger.error(e.getMessage());
            }
            catch (SQLException e) {
                logger.error(e.getMessage());
            }
            catch (FileNotFoundException e) {
                logger.error(e.getMessage());
            }
            catch (IOException e) {
                logger.error(e.getMessage());
            }
            return connection;
        }

    }
}
