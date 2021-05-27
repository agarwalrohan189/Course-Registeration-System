package com.flipkart.dao;

/**
 * author: @subha
 */

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.GradeConstant;
import com.flipkart.constant.RoleConstant;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;

public class AdminDaoOperation implements AdminDaoInterface {
	private static Logger logger = Logger.getLogger(AdminDaoOperation.class);

    private static volatile AdminDaoOperation instance = null;
    private PreparedStatement statement = null;

    /**
     * Constructor
     */
    private AdminDaoOperation(){}

    /**
     * Singleton pattern to generate unique instance of the class
     * @return
     */
    public static AdminDaoOperation getInstance() {
        if (instance == null) {
            synchronized (AdminDaoOperation.class) {
                instance = new AdminDaoOperation();
            }
        }
        return instance;
    }

    /**
     * Method to add course in catalogue using SQL command.
     * @param course -> Course to be added
     * @throws CourseFoundException
     */
    @Override
    public void addCourse(Course course) throws CourseFoundException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = SQLQueriesConstant.ADD_COURSE_QUERY;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setString(3, course.getInstructorId());
            statement.setBoolean(4, true);
            statement.setInt(5, 0);
            int row = statement.executeUpdate();

            logger.info(row + " course added");
            if (row == 0) {
                logger.info("Course with courseCode: " + course.getCourseId() + "not added to catalog.");
                throw new CourseFoundException(course.getCourseId());
            }

            logger.info("Course with courseCode : " + course.getCourseId() + " is added to catalog.");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new CourseFoundException(course.getCourseId());
        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }

    /**
     * Method to remove course from course catalogue using SQL command.
     * @param courseID -> ID of course which is to be removed
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    @Override
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try{
            String sql = SQLQueriesConstant.DELETE_COURSE_QUERY;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, courseID);
            int row = statement.executeUpdate();

            logger.info(row + " entries deleted");
            if (row == 0) {
                logger.info(courseID + " not in catalogue");
                throw new CourseNotFoundException(courseID);
            }
            logger.info("Course with course code : " + courseID + " deleted.");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new CourseNotDeletedException(courseID);
        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }

    /**
     * Adds a professor object to the database using SQL command
     * @param professor : professor object containing the details of the prof
     * @throws ProfNotAddedException
     * @throws ProfFoundException
     */
    @Override
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException {
        try {

            this.addUser(professor);

        }catch (UserNotAddedException e) {

            logger.error(e.getMessage());
            throw new ProfNotAddedException(professor.getId());
        }


        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueriesConstant.ADD_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, professor.getId());
            statement.setString(2, professor.getDepartment());
            statement.setString(3, professor.getQualification());
            statement.setDate(4, new Date(professor.getDateOfJoining().getTime()));
            int row = statement.executeUpdate();

            logger.info(row + " professor added.");
            if(row == 0) {
                logger.info("Professor with professorId: " + professor.getId() + " not added.");
                throw new ProfNotAddedException(professor.getId());
            }

            logger.info("Professor with professorId: " + professor.getId() + " added.");

        }catch(SQLException se) {

            logger.info(se.getMessage());
            throw new ProfFoundException(professor.getId());

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }

    /**
     * Adds a user object to the database using SQL command
     * @param user : user object containing the details of the user
     */
    @Override
    public void addUser(User user) throws UserNotAddedException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueriesConstant.ADD_USER_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setInt(4, GenderConstant.genderToInt(user.getGender()));
            statement.setInt(5, RoleConstant.roleToInt(user.getRole()));
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getUsername());
            statement.setDate(8, new Date(user.getDoB().getTime()));
            int row = statement.executeUpdate();

            logger.info(row + " user added.");
            if(row == 0) {
                logger.info("User with userId: " + user.getId() + " not added.");
                throw new UserNotAddedException(user.getId());
            }

            logger.info("User with userId: " + user.getUsername() + " added.");

        }catch(SQLException se) {

            logger.info(se.getMessage());
            throw new UserNotAddedException(user.getId());

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }

    /**
     * removes a professor object from the database using SQL command
     * @param profID : professor ID of professor to be removed
     * @throws ProfFoundException
     */
    @Override
    public void removeProf(String profID) throws ProfNotFoundException, ProfNotDeletedException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueriesConstant.DELETE_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, profID);
            int row = statement.executeUpdate();

            logger.info(row + " entries deleted.");
            if(row == 0) {
                logger.info("Prof with userId: " + profID + " not deleted.");
                throw new ProfNotFoundException(profID);
            }
            
            String sq2 = SQLQueriesConstant.DELETE_USER_QUERY;
            statement = conn.prepareStatement(sq2);

            statement.setString(1, profID);
            row = statement.executeUpdate();

            logger.info(row + " entries deleted.");
            if(row == 0) {
                logger.info("User with userId: " + profID + " not deleted.");
                throw new ProfNotFoundException(profID);
            }

            logger.info("Prof with userId: " + profID + " deleted.");

        }catch(SQLException se) {

        	if (se.getMessage().contains("Cannot delete or update a parent row: a foreign key constraint fails"))
        		throw new ProfNotDeletedException(profID);
        	else
        	{
        		logger.info(se.getMessage());
            	throw new ProfNotFoundException(profID);
        	}

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }


    /**
     * Remove student from database using SQL command
     * @param studentID -> student which is to be removed
     * @throws UserNotFoundException
     */
    @Override
    public void removeStudent(String studentID) throws StudentNotFoundException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueriesConstant.DELETE_STUDENT_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, studentID);
            int row = statement.executeUpdate();

            logger.info(row + " entries deleted.");
            if(row == 0) {
                logger.info("Student with userId: " + studentID + " not deleted.");
                throw new StudentNotFoundException(studentID);
            }
            
            String sq2 = SQLQueriesConstant.DELETE_USER_QUERY;
            statement = conn.prepareStatement(sq2);

            statement.setString(1, studentID);
            row = statement.executeUpdate();

            logger.info(row + " entries deleted.");
            if(row == 0) {
                logger.info("User with userId: " + studentID + " not deleted.");
                throw new UserNotFoundException(studentID);
            }

            logger.info("User with userId: " + studentID + " deleted.");

        }catch(SQLException se) {

            logger.info(se.getMessage());
            throw new StudentNotFoundException(studentID);

        } catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }

    /**
     * Add professor as instructor in the given course using SQL command.
     * @param profID   -> ID of professor to be added
     * @param courseID -> ID of course which professor is requesting
     */
    @Override
    public void assignProf(String profID, int courseID) throws CourseNotFoundException, ProfNotFoundException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueriesConstant.ASSIGN_COURSE_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, profID);
            statement.setInt(2, courseID);
            int row = statement.executeUpdate();

            logger.info(row + " entries updated.");
            if(row == 0) {
                logger.info("Prof with userId: " + profID + " cannot be assigned to course : " + courseID);
                throw new CourseNotFoundException(courseID);
            }

            logger.info("Prof with userId: " + profID + " assigned to course : " + courseID);

        }catch(SQLException se) {

            logger.info(se.getMessage());
            throw new ProfNotFoundException(profID);

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }

    /**
     * Get primary courses for registration of student
     * @return -> Map containing student ID and corresponding primary courses
     * @throws DatabaseException
     */
    @Override
    public HashMap<String,List<Integer>> getPreferredCourses() throws DatabaseException {
    	Connection conn = DBUtil.getConnection();
    	HashMap<String,List<Integer>> preferredCourseList = new HashMap<>();
		try 
		{
			statement = conn.prepareStatement(SQLQueriesConstant.VIEW_PREFERRED_COURSES);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				List<Integer> courses;
				if(preferredCourseList.containsKey(rs.getString("sid"))) {
					courses = preferredCourseList.remove(rs.getString("sid"));
				}
				else {
					courses = new ArrayList<>();
				}
				courses.add(rs.getInt("cid"));
				preferredCourseList.put(rs.getString("sid"), courses);
			}
			
			return preferredCourseList;
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
     * Get alternate courses for registration of student
     * @return -> Map containing student ID and corresponding alternate courses
     * @throws DatabaseException
     */
    @Override
    public HashMap<String,List<Integer>> getAlternateCourses() throws DatabaseException {
    	Connection conn = DBUtil.getConnection();
    	HashMap<String,List<Integer>> alternateCourseList = new HashMap<>();
		try 
		{
			statement = conn.prepareStatement(SQLQueriesConstant.VIEW_ALTERNATE_COURSES);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				List<Integer> courses;
				if(alternateCourseList.containsKey(rs.getString("sid"))) {
					courses = alternateCourseList.remove(rs.getString("sid"));
				}
				else {
					courses = new ArrayList<>();
				}
				courses.add(rs.getInt("cid"));
				alternateCourseList.put(rs.getString("sid"), courses);
			}
			
			return alternateCourseList;
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
    
    @Override
    public void setRegistrationStatus(String studentID) throws SQLException{
    	Connection conn = DBUtil.getConnection();
        statement = null;
        String sql = SQLQueriesConstant.SET_REGISTRATION_STATUS;
        statement = conn.prepareStatement(sql);
        statement.setString(1,studentID);
        statement.executeUpdate();
    }

    /**
     * Method to generate Report card of student using SQL command.
     * @param studentID -> ID of student whose report card is being generated
     */
    @Override
    public void generateReportCard(String studentID) throws StudentNotFoundException{
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = SQLQueriesConstant.GET_STUDENT_GRADES;
            statement = conn.prepareStatement(sql);
            statement.setString(1,studentID);

            ResultSet resultSet = statement.executeQuery();

            HashMap<Integer,Integer> reportCard = new HashMap<Integer, Integer>();

            while (resultSet.next()){
                reportCard.put(resultSet.getInt("cid"),resultSet.getInt("grade"));
            }

            Iterator<Map.Entry<Integer,Integer>> it = reportCard.entrySet().iterator();

            float cpi = 0;
            int courses = 0;

            logger.info("CourseID\t\tGrade");

            while (it.hasNext()){
                Map.Entry<Integer,Integer> entry = it.next();
                courses+=1;cpi+=entry.getValue();
                logger.info(entry.getKey()+"\t\t\t"+entry.getValue());
            }

            cpi = cpi/(float) courses;

            logger.info("CPI :" + cpi);

        }catch (Exception e){
            throw new StudentNotFoundException(studentID);
        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
    }

    /**
     * Method to get list of courses in catalog
     * @return List of courses in CourseCatalogue
     */
    @Override
    public List<Course> viewCourses() {
        statement = null;
        List<Course> coursesList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        try{
            String sql = SQLQueriesConstant.GET_COURSE_CATALOGUE;
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String pid = resultSet.getString(3);
//                sql = SQLQueries.GET_PROFESSOR_NAME;
//                statement = conn.prepareStatement(sql);
//                statement.setString(1, pid);
//                ResultSet resultSet1 = statement.executeQuery();
                String profName;
                if (pid.equals("p0"))
                {
                	profName = "NA";
                	pid = "NA";
                }
                else
                	profName = UserDaoOperation.getInstance().getDetails(pid).getName();

                Course course = new Course(resultSet.getInt(1), resultSet.getString(2),
                		pid, profName, resultSet.getInt(5));
                coursesList.add(course);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
		}
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				logger.info(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
                    logger.error(e.getMessage());
				}
			}
		}
        return coursesList;
    }

    /**
     * Method to ensure no course has less than three registered students
     */
    @Override
    public void validateRegistration() throws CourseNotDeletedException{
        statement = null;
        List<Course> courseList = viewCourses();
        for (Course course : courseList) {
            if (course.getFilledSeats() < 3) {
                course.setFilledSeats(0);
                Connection conn = DBUtil.getConnection();
                try {
                    setFilledSeats(0,course.getCourseId());
                    String sql = SQLQueriesConstant.DELETE_REGISTERED_COURSE_QUERY;
                    statement = conn.prepareStatement(sql);
                    statement.setInt(1, course.getCourseId());
                    statement.executeUpdate();

                   
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    throw new CourseNotDeletedException(course.getCourseId());
                }
                finally {
        			try {
        				conn.close();
        			}
        			catch(SQLException ex){
        				logger.info(ex.getMessage());
        				try {
        					throw new DatabaseException();
        				} catch (DatabaseException e) {
                            logger.error(e.getMessage());
        				}
        			}
        		}
            }
        }
        logger.info("Validation complete!");
    }

    /**
     * Set filled seats of the given course
     * @param num -> number of seats
     * @param courseID -> course ID whose seats are being set
     * @throws SQLException
     */
    private void setFilledSeats(int num, int courseID)throws SQLException {
    	Connection conn = DBUtil.getConnection();
        statement = null;
        String sql = SQLQueriesConstant.SET_FILLED_SEATS;
        statement = conn.prepareStatement(sql);
        statement.setInt(1,num);
        statement.setInt(2,courseID);
        statement.executeUpdate();
    }

    /**
     * Delete chosen courses from database
     * @throws SQLException
     */
    public void deleteChosenCourses() throws SQLException{
    	Connection conn = DBUtil.getConnection();
        statement = null;
        String sql = SQLQueriesConstant.CLEAR_CHOSEN_COURSES;
        statement = conn.prepareStatement(sql);
        statement.executeUpdate();
    }

    /**
     *  Method to approve Student
     *  @param studentId -> ID of student to be approved
     *  @throws StudentNotFoundException
     */
    @Override
    public void approveStudent(String studentId) throws StudentNotFoundException {
        Connection conn = DBUtil.getConnection();
        statement = null;
        try {
            String sql = SQLQueriesConstant.APPROVE_STUDENT_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1,studentId);
            int row = statement.executeUpdate();

            logger.info(row + " student approved.");
            if(row == 0) {
                //logger.error("Student with studentId: " + studentId + " not found.");
                throw new StudentNotFoundException(studentId);
            }

            //logger.info("Student with studentId: " + studentId + " approved by admin.");

        }catch(SQLException se) {
            //logger.error(se.getMessage());
        }

    }

    /**
     * Method to fetch the list of all enrolled professors
     *
     * @return List of Professors in table Professors
     */
    @Override
    public List<Professor> viewProfessors() {
        Connection connection = DBUtil.getConnection();
        statement = null;
        List<Professor> professorList = new ArrayList<Professor>();
        try {

            String sql = SQLQueriesConstant.VIEW_PROFESSOR_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String pid = resultSet.getString("id");
                Professor professor = (Professor) UserDaoOperation.getInstance().getDetails(pid);
                professorList.add(professor);
            }

            logger.info("Professor list generated");

        }catch(SQLException se) {
            logger.error(se.getMessage());
        } catch (UserNotFoundException e) {
            //
        } catch (StudentNotFoundException e) {
            //logger.error(e.getMessage());
        } catch (ProfNotFoundException e) {
            //logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();
                } catch (DatabaseException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        return professorList;
    }

    /**
     * Method view pending approvals of students
     */
    @Override
    public List<Student> viewPending() {
        Connection connection = DBUtil.getConnection();
        statement = null;
        List<Student> studentList  = new ArrayList<Student>();
        try{
            String sql = SQLQueriesConstant.VIEW_PENDING_STUDENTS;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String sid = resultSet.getString("id");
                Student student = (Student) UserDaoOperation.getInstance().getDetails(sid);
                studentList.add(student);
            }
        }catch (SQLException se){
            logger.error(se.getMessage());
        } catch (UserNotFoundException e) {
            //logger.error(e.getMessage());
        } catch (StudentNotFoundException e) {
            //logger.error(e.getMessage());
        } catch (ProfNotFoundException e) {
            //logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
                try {
                    throw new DatabaseException();
                } catch (DatabaseException e) {
                    // TODO Auto-generated catch block
                    logger.error(e.getMessage());
                }
            }
        }
        return studentList;
    }
}
