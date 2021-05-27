package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.constant.GradeConstant;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exception.GradeNotAssignedException;
import com.flipkart.exception.ProfNotFoundException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class ProfessorOperation implements ProfessorInterface{

    private static volatile ProfessorOperation instance = null;
    ProfessorDaoOperation professorDaoOperation = ProfessorDaoOperation.getInstance();

    private ProfessorOperation(){}

    public static ProfessorOperation getInstance() {
        if (instance == null) {
            synchronized (ProfessorOperation.class) {
                instance = new ProfessorOperation();
            }
        }
        return instance;
    }

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
    public List<Student> viewStudent(int courseID, String profID) throws ProfNotFoundException, StudentNotFoundException{
        List<Student> enrolledStudents = new ArrayList<Student>();

        try {
            enrolledStudents = professorDaoOperation.viewStudent(courseID,profID);
        }catch (Exception e){
            throw e;
        }

        return enrolledStudents;
    }

    @Override
    public void assignGrade( String studentID, int courseID, GradeConstant grade) throws GradeNotAssignedException, StudentNotFoundException {
        try{
            professorDaoOperation.assignGrade(studentID,courseID,grade);
        }catch (Exception e){
            throw e;
        }
    }
}
