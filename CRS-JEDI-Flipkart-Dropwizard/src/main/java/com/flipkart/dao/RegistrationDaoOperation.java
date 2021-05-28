/**
 * 
 */
package com.flipkart.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flipkart.constant.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;

/**
 * @author shubh
 *
 */
public class RegistrationDaoOperation implements RegistrationDaoInterface {
	private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);

    private static volatile RegistrationDaoOperation instance = null;
    private PreparedStatement statement = null;

    /**
     * Constructor
     */
    private RegistrationDaoOperation(){}

    /**
     * Singleton Pattern for getting only one class instance
     * @return -> Instance of Class
     */
    public static RegistrationDaoOperation getInstance() {
        if (instance == null) {
            synchronized (RegistrationDaoOperation.class) {
                instance = new RegistrationDaoOperation();
            }
        }
        return instance;
    }

    /**
     * Register selected courses
     * @param studentId
     */
	@Override
	public void registerCourses(String studentId, HashMap<Integer,Boolean> courseIDs) throws StudentNotFoundException{
		 Connection conn = DBUtil.getConnection();
		 try 
		 {      
			 for (Map.Entry<Integer,Boolean> courseElement : courseIDs.entrySet()) {
				 statement = conn.prepareStatement(SQLQueriesConstant.CHOOSE_COURSE);
		            statement.setString(1, studentId);
		            statement.setInt(2, courseElement.getKey());
		            statement.setBoolean(3, courseElement.getValue());
		            statement.executeUpdate();
			 }
		 }
		 catch (SQLException e) 
         {
             logger.error(e.getMessage());
             throw new StudentNotFoundException(studentId);
         }
         finally
         {
             try{
                 statement.close();
                 conn.close();
             }
             catch(Exception e){
                 logger.error("Couldn't close connection to database");
                 logger.error(e.getMessage());
             }
         }
	}

    /**
     * Get details of the course from course ID
     * @param courseId -> Course ID of course
     * @return -> Course
     * @throws CourseNotFoundException
     */
    @Override
    public Course getCourse(int courseId) throws CourseNotFoundException{
        Connection conn = DBUtil.getConnection();
        try
        {
            statement = conn.prepareStatement("Select * from CourseCatalogue where cid = ?");
            statement.setInt(1,courseId);
            ResultSet rs = statement.executeQuery();
            
            if (!rs.next())
            	throw new CourseNotFoundException(courseId); 
            else
            {
            	Course course = new Course(rs.getInt("cid"), rs.getString("cname"), rs.getString("pid"), UserDaoOperation.getInstance().getDetails(rs.getString("pid")).getName(), rs.getInt("filledSeats"));
            	return course;
            }
        }
        catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new CourseNotFoundException(courseId);
        } catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ProfNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
        return null;
	}

    /**
     * Add a course
     * @param studentId
     * @param courseCode
     * @return -> Whether course is added or not
     * @throws CourseNotFoundException
     * @throws CourseLimitExceededException
     * @throws CourseSeatsFullException
     */
	@Override
	public boolean addCourse(String studentId, int courseCode) throws DatabaseException{
        Connection conn = DBUtil.getConnection();
        try
        {
            statement = conn.prepareStatement(SQLQueriesConstant.ADD_COURSE);
            statement.setString(1, studentId);
            statement.setInt(2, courseCode);
            statement.setInt(3, Year.now().getValue());
            statement.setInt(4, (LocalDate.now().getMonthValue()>6?2:1));
            statement.executeUpdate();
            
            statement = conn.prepareStatement(SQLQueriesConstant.INCREMENT_COURSE_SEATS);
            statement.setInt(1, courseCode);
            statement.executeUpdate();
            return true;
        }
        catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new DatabaseException();
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
	}

    /**
     * Drop a course
     * @param studentId
     * @param courseCode
     * @return -> Whether course is dropped or not
     * @throws CourseNotFoundException
     */
	@Override
	public boolean dropCourse(String studentId, int courseCode) throws DatabaseException{
		Connection conn = DBUtil.getConnection();	
        try
        {
            statement = conn.prepareStatement(SQLQueriesConstant.DROP_COURSE);
            statement.setInt(1, courseCode);
            statement.setString(2, studentId);
            statement.execute();
            
            statement = conn.prepareStatement(SQLQueriesConstant.DECREMENT_COURSE_SEATS);
            statement.setInt(1, courseCode);
            statement.execute();
            
            statement.close();
            
            return true;
        }
        catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new DatabaseException();
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
	}

    /**
     * View registered courses
     * @param studentId
     * @return -> List of registered courses
     */
	@Override
	public List<RegisteredCourse> viewRegisteredCourses(String studentId) throws StudentNotFoundException{
		Connection conn = DBUtil.getConnection();
		List<RegisteredCourse> registeredCourseList = new ArrayList<>();
		try 
		{
			statement = conn.prepareStatement(SQLQueriesConstant.VIEW_REGISTERED_COURSES);
			statement.setString(1, studentId);

			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				RegisteredCourse course = new RegisteredCourse(rs.getString("cname"), UserDaoOperation.getInstance().getDetails(rs.getString("pid")).getName(), studentId, rs.getInt("semesterNum"), rs.getInt("cid"), GradeConstant.fromInt(rs.getInt("grade")));			
                registeredCourseList.add(course);
			}
		}
        catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new StudentNotFoundException(studentId);
        } catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		} catch (ProfNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
		return registeredCourseList;
	}

    /**
     * Calculate fee of the student
     * @param studentId -> Student ID of student whose fees are calculated
     * @return Fee to be paid
     */
	@Override
	public float calculateFee(String studentId) throws StudentNotFoundException{
		int num_courses = viewRegisteredCourses(studentId).size();;
		return num_courses * SQLQueriesConstant.feesPerCourse;
	}

    /**
     * Confirm the payment made
     * @param studentId -> student ID of student whose confirmation is to be done
     * @throws StudentNotFoundException
     */
	@Override
	public void feePaid(String studentId) throws StudentNotFoundException{
		Connection conn = DBUtil.getConnection();
		try 
		{
			statement = conn.prepareStatement(SQLQueriesConstant.SET_PAYMENT_STATUS);
			statement.setString(1, studentId);
			statement.executeUpdate();
		} 
		catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
	}

	/**
     * Check whether registration is done or not
	 * @param studentId -> Student ID of student whose registration is being checked
	 * @return -> Whether registration is done or not
	 * @throws StudentNotFoundException
	 */
	@Override
	public boolean isRegistrationDone(String studentId) throws StudentNotFoundException {
		Connection conn = DBUtil.getConnection();
		boolean regDone;
		try 
		{
			statement = conn.prepareStatement(SQLQueriesConstant.GET_STUDENT_DETAILS_QUERY);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			regDone = rs.getBoolean("isRegistered");
		} 
		catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
		return regDone;
	}

    /**
     * Method to check whether payment is done or not
     * @param studentId -> Student ID of student whose payment we're checking
     * @return -> whether payment is done or not
     * @throws StudentNotFoundException
     */
	@Override
	public boolean isPaymentDone(String studentId) throws StudentNotFoundException  {
		Connection conn = DBUtil.getConnection();
		boolean paid;
		try 
		{
			statement = conn.prepareStatement(SQLQueriesConstant.GET_STUDENT_DETAILS_QUERY);
			statement.setString(1, studentId);
			ResultSet rs = statement.executeQuery();
			rs.next();
			paid = rs.getBoolean("paymentIsDone");
		} 
		catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new StudentNotFoundException(studentId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
		return paid;
	}

    /**
     * Method to get notification
     * @param notifId -> ID of notification
     * @return -> Message of notification
     * @throws NotifIdNotExistsException
     */
	@Override
	public String getNotification(int notifId) throws NotifIdNotExistsException{
		Connection conn = DBUtil.getConnection();
		String notifMessage;
		try 
		{
			statement = conn.prepareStatement(SQLQueriesConstant.GET_NOTIFICATION_MESSAGE);
			statement.setString(1, Integer.toString(notifId));
			ResultSet rs = statement.executeQuery();
			rs.next();
			notifMessage = rs.getString("message");
		} 
		catch (SQLException e) 
        {
            logger.error(e.getMessage());
            throw new NotifIdNotExistsException(notifId);
        }
        finally
        {
            try{
                statement.close();
                conn.close();
            }
            catch(Exception e){
                logger.error("Couldn't close connection to database");
                logger.error(e.getMessage());
            }
        }
		return notifMessage;
	}

}