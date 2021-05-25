package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProfessorOperation implements ProfessorInterface{

    ProfessorDaoOperation professorDaoOperation = new ProfessorDaoOperation();

    @Override
    public List<Course> viewCourses(String profID) throws ProfNotFoundException {
        List<Course> courses = new ArrayList<Course>();
        try {
            courses = professorDaoOperation.viewCourses(profID);
        }catch (Exception e){
            throw e;
        }
        return courses;
    }

    @Override
    public List<EnrolledStudent> viewStudent(int courseID, String profID) throws ProfNotFoundException{
        List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();

        try {
            enrolledStudents = professorDaoOperation.viewStudent(courseID,profID);
        }catch (Exception e){
            throw e;
        }

        return enrolledStudents;
    }

    @Override
    public void assignGrade( String studentID, int courseID, String grade) throws GradeNotAssignedException, StudentNotFoundException {
        try{
            professorDaoOperation.assignGrade(studentID,courseID,grade);
        }catch (Exception e){
            throw e;
        }
    }
}
