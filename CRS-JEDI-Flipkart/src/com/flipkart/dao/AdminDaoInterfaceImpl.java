package com.flipkart.dao;

/**
 * author: @subha
 */

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class AdminDaoInterfaceImpl implements AdminDaoInterface {

    private static volatile AdminDaoInterfaceImpl instance = null;
    private static Logger logger = Logger.getLogger(String.valueOf(AdminDaoInterfaceImpl.class));
    private PreparedStatement statement = null;

    public AdminDaoInterfaceImpl(){}

    public static AdminDaoInterfaceImpl getInstance() {
        if (instance == null) {
            synchronized (AdminDaoInterfaceImpl.class) {
                instance = new AdminDaoInterfaceImpl();
            }
        }
        return instance;
    }
    /**
     * Method to add course in catalogue using SQL command.
     *
     * @param course -> Course to be added
     * @throws CourseFoundException
     */
    @Override
    public void addCourse(Course course) throws CourseFoundException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = SQLQueries.ADD_COURSE_QUERY;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setString(3, null);
            statement.setBoolean(4, true);
            statement.setInt(5, 0);
            int row = statement.executeUpdate();

            System.out.println(row + " course added");
            if (row == 0) {
                System.out.println("Course with courseCode: " + course.getCourseId() + "not added to catalog.");
                throw new CourseFoundException(course.getCourseId());
            }

            System.out.println("Course with courseCode : " + course.getCourseId() + " is added to catalog.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new CourseFoundException(course.getCourseId());
        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * Method to remove course from course catalogue using SQL command.
     *
     * @param courseID -> ID of course which is to be removed
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    @Override
    public void removeCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try{
            String sql = SQLQueries.DELETE_COURSE_QUERY;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, courseID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries deleted");
            if (row == 0) {
                System.out.println(courseID + " not in catalogue");
                throw new CourseNotFoundException(courseID);
            }
            System.out.println("Course with course code : " + courseID + " deleted.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new CourseNotDeletedException(courseID);
        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * To validate course registration of a student using SQL command.
     *
     * @param studentID          -> ID of student to be added
     * @param registeredStudents -> List of registered students in the course
     * @throws StudentNotRegisteredException
     * @throws StudentNotFoundException
     */
    @Override
    public void validateRegistration(String studentID, List<Student> registeredStudents) throws StudentNotRegisteredException, StudentNotFoundException {

    }

    /**
     * Adds a professor object to the database using SQL command
     *
     * @param professor : professor object containing the details of the prof
     * @throws ProfNotAddedException
     * @throws ProfFoundException
     */
    @Override
    public void addProf(Professor professor) throws ProfNotAddedException, ProfFoundException {
        try {

            this.addUser(professor);

        }catch (UserNotAddedException e) {

            e.printStackTrace();
            throw new ProfNotAddedException(professor.getId());
        }


        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueries.ADD_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, professor.getId());
            statement.setString(2, professor.getDepartment());
            statement.setString(3, professor.getQualification());
            statement.setDate(4, new Date(professor.getDateOfJoining().getTime()));
            int row = statement.executeUpdate();

            System.out.println(row + " professor added.");
            if(row == 0) {
                System.out.println("Professor with professorId: " + professor.getId() + " not added.");
                throw new ProfNotAddedException(professor.getId());
            }

            System.out.println("Professor with professorId: " + professor.getId() + " added.");

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new ProfFoundException(professor.getId());

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * Adds a user object to the database using SQL command
     *
     * @param user : user object containing the details of the user
     */
    @Override
    public void addUser(User user) throws UserNotAddedException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueries.ADD_USER_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setInt(4, Gender.genderToInt(user.getGender()));
            statement.setInt(5, Role.roleToInt(user.getRole()));
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getUsername());
            statement.setDate(8, new Date(user.getDoB().getTime()));
            int row = statement.executeUpdate();

            System.out.println(row + " user added.");
            if(row == 0) {
                System.out.println("User with userId: " + user.getId() + " not added.");
                throw new UserNotAddedException(user.getId());
            }

            System.out.println("User with userId: " + user.getUsername() + " added.");

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new UserNotAddedException(user.getId());

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * removes a professor object from the database using SQL command
     *
     * @param profID : professor ID of professor to be removed
     * @throws ProfFoundException
     */
    @Override
    public void removeProf(String profID) throws ProfNotFoundException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueries.DELETE_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, profID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries deleted.");
            if(row == 0) {
                System.out.println("Prof with userId: " + profID + " not deleted.");
                throw new ProfNotFoundException(profID);
            }

            System.out.println("Prof with userId: " + profID + " deleted.");

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new ProfNotFoundException(profID);

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * Adds student to the database using SQL command
     *
     * @param student -> student to be added
     * @throws UserAlreadyExistsException
     */
    @Override
    public void addStudent(Student student) throws StudentAlreadyExistsException, StudentNotAddedException {
    	
    	try {

            this.addUser(student);

        }catch (UserNotAddedException e) {

            e.printStackTrace();
            throw new StudentNotAddedException(student.getId());
        }
    	
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueries.ADD_STUDENT;
            statement = conn.prepareStatement(sql);

            statement.setString(1, student.getId());
            statement.setString(2, student.getBranch());
            statement.setInt(3, student.getBatchYear());
            statement.setBoolean(4, student.isPaymentDone());
            int row = statement.executeUpdate();

            System.out.println(row + " student added.");
            if(row == 0) {
                System.out.println("Student with userId: " + student.getId() + " not added.");
                throw new StudentAlreadyExistsException(student.getId());
            }

            System.out.println("User with userId: " + student.getUsername() + " added.");

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new StudentNotAddedException(student.getId());

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * Remove student from database using SQL command
     *
     * @param studentID -> student which is to be removed
     * @throws UserNotFoundException
     */
    @Override
    public void removeStudent(String studentID) throws StudentNotFoundException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueries.DELETE_STUDENT_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, studentID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries deleted.");
            if(row == 0) {
                System.out.println("Student with userId: " + studentID + " not deleted.");
                throw new StudentNotFoundException(studentID);
            }

            System.out.println("User with userId: " + studentID + " deleted.");

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new StudentNotFoundException(studentID);

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * Add professor as instructor in the given course using SQL command.
     *
     * @param profID   -> ID of professor to be added
     * @param courseID -> ID of course which professor is requesting
     */
    @Override
    public void assignProf(String profID, int courseID) throws CourseNotFoundException, ProfNotFoundException {
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {

            String sql = SQLQueries.ASSIGN_COURSE_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, profID);
            statement.setInt(2, courseID);
            int row = statement.executeUpdate();

            System.out.println(row + " entries updated.");
            if(row == 0) {
                System.out.println("Prof with userId: " + profID + " cannot be assigned to course : " + courseID);
                throw new CourseNotFoundException(courseID);
            }

            System.out.println("Prof with userId: " + profID + " assigned to course : " + courseID);

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new ProfNotFoundException(profID);

        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * Method to generate Report card of student using SQL command.
     *
     * @param studentID -> ID of student whose report card is being generated
     */
    @Override
    public void generateReportCard(String studentID) throws StudentNotFoundException{
        statement = null;
        Connection conn = DBUtil.getConnection();
        try {
            String sql = SQLQueries.GET_STUDENT_GRADES;
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

            System.out.println("CourseID-------Grade");

            while (it.hasNext()){
                Map.Entry<Integer,Integer> entry = it.next();
                courses+=1;cpi+=entry.getValue();
                System.out.println(entry.getKey() + "---" + entry.getValue());
            }

            cpi = cpi/(float) courses;

            System.out.println("*_*_*_*_*_*_* CPI - " + cpi +  " *_*_*_*_*_*_*");

        }catch (Exception e){
            throw new StudentNotFoundException(studentID);
        }
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    /**
     * Method to get list of courses in catalog
     *
     * @return List of courses in CourseCatalogue
     */
    @Override
    public List<Course> viewCourses() {
        statement = null;
        List<Course> coursesList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        try{
            String sql = SQLQueries.GET_COURSE_CATALOGUE;
            statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String pid = resultSet.getString(3);
//                sql = SQLQueries.GET_PROFESSOR_NAME;
//                statement = conn.prepareStatement(sql);
//                statement.setString(1, pid);
//                ResultSet resultSet1 = statement.executeQuery();
                String profName = UserDAOOperation.getInstance().getDetails(pid).getName();

                Course course = new Course(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), profName, resultSet.getInt(5));
                coursesList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StudentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProfNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
			try {
				conn.close();
			}
			catch(SQLException ex){
				System.out.println(ex.getMessage());
				try {
					throw new DatabaseException();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
                //TODO: notify student that the course has been cancelled
                Connection conn = DBUtil.getConnection();
                try {
                    String sql = SQLQueries.DELETE_REGISTERED_COURSE_QUERY;
                    statement = conn.prepareStatement(sql);
                    statement.setInt(1, course.getCourseId());
                    int row = statement.executeUpdate();

                    System.out.println(row + " entries deleted");
                    if (row == 0) {
                        throw new CourseNotDeletedException(course.getCourseId());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new CourseNotDeletedException(course.getCourseId());
                }
                finally {
        			try {
        				conn.close();
        			}
        			catch(SQLException ex){
        				System.out.println(ex.getMessage());
        				try {
        					throw new DatabaseException();
        				} catch (DatabaseException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        			}
        		}
            }
        }
        System.out.println("Validation complete!");
    }
}
