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

public class ProfessorDaoOperation implements ProfessorDaoInterface{

    @Override
    public List<Course> viewCourses(String profID) throws ProfNotFoundException {
        Connection connection = DBUtil.getConnection();

        List<Course> courseList = new ArrayList<Course>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_COURSES);

            preparedStatement.setString(1, profID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                courseList.add(new Course(resultSet.getInt("cid"),resultSet.getString("cname"),resultSet.getString("pid"),
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
