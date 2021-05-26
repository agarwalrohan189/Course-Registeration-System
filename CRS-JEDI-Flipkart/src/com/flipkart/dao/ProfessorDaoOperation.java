package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
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
                        resultSet.getString("name"),resultSet.getInt("filledSeats")));
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
    public List<EnrolledStudent> viewStudent(int courseID, String profID) throws ProfNotFoundException{

        Connection connection = DBUtil.getConnection();

        List<EnrolledStudent> studentList = new ArrayList<EnrolledStudent>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_ENROLLED_STUDENTS);

            preparedStatement.setString(1,profID);
            preparedStatement.setInt(2,courseID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                studentList.add(new EnrolledStudent(resultSet.getString("id"), resultSet.getString("name")));
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
