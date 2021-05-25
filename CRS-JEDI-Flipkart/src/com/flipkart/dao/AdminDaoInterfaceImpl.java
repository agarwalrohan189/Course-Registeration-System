package com.flipkart.dao;

/**
 * author: @subha
 */

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class AdminDaoInterfaceImpl implements AdminDaoInterface {

    private static volatile AdminDaoInterfaceImpl instance = null;
    private static Logger logger = Logger.getLogger(String.valueOf(AdminDaoInterfaceImpl.class));
    private PreparedStatement statement = null;

    private AdminDaoInterfaceImpl(){}

    public static AdminDaoInterfaceImpl getInstance() {
        if (instance == null) {
            synchronized (AdminDaoInterfaceImpl.class) {
                instance = new AdminDaoInterfaceImpl();
            }
        }
        return instance;
    }

    Connection conn = DBUtil.getConnection();
    /**
     * Method to add course in catalogue using SQL command.
     *
     * @param course -> Course to be added
     * @throws CourseFoundException
     */
    @Override
    public void addCourse(Course course) throws CourseFoundException {
        statement = null;
        try {
            String sql = SQLQueries.ADD_COURSE_QUERY;
            statement = conn.prepareStatement(sql);
            statement.setInt(1, course.getCourseId());
            statement.setString(2, course.getCourseName());
            statement.setString(3, null);

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
            throw new CourseNotFoundException(courseID);
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
        try {

            String sql = SQLQueries.ADD_PROFESSOR_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, professor.getId());
            statement.setString(2, professor.getDepartment());
            statement.setString(3, professor.getQualification());
            statement.setDate(2, (Date) professor.getDateOfJoining());
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
    }

    /**
     * Adds a user object to the database using SQL command
     *
     * @param user : user object containing the details of the user
     */
    @Override
    public void addUser(User user) throws UserNotAddedException {
        statement = null;
        try {

            String sql = SQLQueries.ADD_USER_QUERY;
            statement = conn.prepareStatement(sql);

            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getGender().toString());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getAddress());
            statement.setString(6, user.getUsername());
            statement.setDate(6, (Date) user.getDoB());
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
    }

    /**
     * Adds student to the database using SQL command
     *
     * @param student -> student to be added
     * @throws UserAlreadyExistsException
     */
    @Override
    public void addStudent(Student student) throws StudentAlreadyExistsException, StudentNotAddedException {
        statement = null;
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
    }

    /**
     * Method to generate Report card of student using SQL command.
     *
     * @param studentID -> ID of student whose report card is being generated
     */
    @Override
    public void generateReportCard(String studentID) {

    }
}
