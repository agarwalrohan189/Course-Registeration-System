package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.Grade;
import com.flipkart.constant.SQLQueries;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Dao class for professor operations
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface{

    private static volatile ProfessorDaoOperation instance = null;

    /**
     * Constructor
     */
    private ProfessorDaoOperation(){}

    /**
     * Singleton pattern to get only one instance of the class
     * @return Instance of the class
     */
    public static ProfessorDaoOperation getInstance() {
        if (instance == null) {
            synchronized (ProfessorDaoOperation.class) {
                instance = new ProfessorDaoOperation();
            }
        }
        return instance;
    }

    /**
     * Method to view course which professor is teaching
     * @return -> List of courses professor is teaching
     * @throws ProfNotFoundException
     */
    @Override
    public List<Course> viewCourses(String profID) throws ProfNotFoundException {
        Connection connection = DBUtil.getConnection();

        List<Course> courseList = new ArrayList<Course>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_COURSES);

            preparedStatement.setString(1, profID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                courseList.add(new Course(resultSet.getInt("cid"),resultSet.getString("cname"),profID,
                		UserDAOOperation.getInstance().getDetails(resultSet.getString("pid")).getName(),resultSet.getInt("filledSeats")));
            }
        }catch (Exception e){
            throw new ProfNotFoundException(profID);
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return courseList;
    }

    /**
     * Return enrolled student list for given course.
     * @param courseID -> ID of course whose students are requested.
     * @return -> List of students enrolled in course.
     * @throws ProfNotFoundException
     * @throws StudentNotFoundException
     */
    @Override
    public List<Student> viewStudent(int courseID, String profID) throws ProfNotFoundException, StudentNotFoundException{

        Connection connection = DBUtil.getConnection();

        List<Student> studentList = new ArrayList<Student>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_ENROLLED_STUDENTS);

            preparedStatement.setInt(1,courseID);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> sid = new ArrayList<String>();

            while (resultSet.next()){
                sid.add(resultSet.getString("sid"));
            }

            String curid = "";
            try {
                UserDAOOperation userDAOOperation = UserDAOOperation.getInstance();
                for(String id:sid){
                    curid = id;
                    Student student = (Student) userDAOOperation.getDetails(id);
                    studentList.add(student);
                }
            }catch (Exception e){
                throw new StudentNotFoundException(curid);
            }

        }catch (Exception e){
            throw new ProfNotFoundException(profID);
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return studentList;
    }

    /**
     * Method to assign grade to student.
     * @param studentID -> ID of student to whom grade will be assigned.
     * @param courseID -> ID of course in which grade is given.
     * @param grade -> Grade given to student.
     * @throws StudentNotFoundException
     * @throws GradeNotAssignedException
     */
    @Override
    public void assignGrade(String studentID, int courseID, Grade grade) throws StudentNotFoundException, GradeNotAssignedException{
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ADD_GRADE);

            preparedStatement.setInt(1,grade.hasValue());
            preparedStatement.setInt(2,courseID);
            preparedStatement.setString(3,studentID);

            int r = preparedStatement.executeUpdate();

            if(r!=1){
                throw new GradeNotAssignedException(studentID);
            }

        }catch (Exception e){
            throw new StudentNotFoundException(studentID);
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
