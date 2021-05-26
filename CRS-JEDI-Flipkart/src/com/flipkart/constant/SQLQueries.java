/**
 * 
 */
package com.flipkart.constant;

/**
 * @author rohanagarwal
 *
 */
public class SQLQueries {
	
		//UserDao queries
		public static final String GET_USER_DETAILS_QUERY = "select * from Users where id = ?";
		public static final String GET_USER_NAME = "select name from Users where id = ?";
		public static final String ADD_USER_QUERY = "insert into Users(id, password, name, gender, role, address, username, dob) values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		// public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
		// public static final String GET_ROLE="select role from user where userId = ? ";
		// public static final String UPDATE_PASSWORD="update user set password= ? where userId = ? ";
		// public static final String GET_PROF_NAME = "select name from user where userId = ?";
		
		//ProfessorDao queries
		public static final String GET_PROFESSOR_DETAILS_QUERY = "select * from Professors where id = ?";
		public static final String GET_PROFESSOR_NAME = "select name from Professors where id = ?";
		public static final String ADD_PROFESSOR_QUERY = "insert into Professors(id, department, qualification, doj) values (?, ?, ?, ?)";
		public static final String DELETE_PROFESSOR_QUERY = "delete from Professors where id = ?";
	
		//AdminDao Queries
		public static final String GET_ADMIN_DETAILS_QUERY = "select * from Admins where id = ?";
		
		
		//Student Queries
		public static final String ADD_STUDENT="insert into Students (id,branch,batchYear,paymentIsDone) values (?,?,?,?)";
		public static final String DELETE_STUDENT_QUERY = "delete from Students where id = ?";
		public static final String GET_STUDENT_DETAILS_QUERY = "select * from Students where id = ?";
		
		// CourseCatalogue Queries
		public static final String GET_COURSE_CATALOGUE="select * from CourseCatalogue where valid = true";
		public static final String GET_COURSE_NAME="select cname from CourseCatalogue where cid = ?";
		public static final String ASSIGN_COURSE_QUERY = "update CourseCatalogue set pid = ? where cid = ?";
		public static final String DELETE_COURSE_QUERY = "delete from CourseCatalogue where cid = ?";
		public static final String ADD_COURSE_QUERY = "insert into CourseCatalogue(cid, cname, pid, valid) values (?, ?, ?, ?)";
		
		//RegisteredCourse Queries
		public static final String GET_REGISTERED_COURSE_DETAILS="select * from RegistedCourse where sid = ?";
				
		public static final String CHECK_COURSE_AVAILABILITY=" select courseCode from RegisteredCourse where studentId = ? ";

		public static final String VIEW_GRADE = "select course.courseCode,course.courseName,RegisteredCourse.grade from course inner join RegisteredCourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?;";	
		public static final String ADD_GRADE="update RegisteredCourse set Grade=? where courseCode=? and studentId=?";
		
		//AdminDao Queries
		
		public static final String VIEW_PENDING_ADMISSION_QUERY = "select userId, name, password, role, gender, address, country, studentId from student natural join user where isApproved = 0";
		public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = 1 where studentId = ?";
		
		public static final String VIEW_PROFESSOR_QUERY = "select userId, name, gender, department, designation, address, country from Professor natural join User";
		
		// public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
		// public static final String GET_ROLE="select role from user where userId = ? ";
		// public static final String IS_APPROVED="select isApproved from student where studentId = ? ";
		// public static final String GET_STUDENT_ID="select studentId from student where userId = ? ";
		// public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
		// public static final String GET_PROF_NAME = "select name from user where userId = ?";
	
		// Student Queries
		// public static final String VIEW_AVAILABLE_COURSES=" select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId = ?) and course.isOffered = ? and seats > 0";
		// public static final String CHECK_COURSE_AVAILABILITY=" select courseCode from registeredcourse where studentId = ? ";
		
		//Registration
		public static final String ADD_COURSE = "insert into RegisteredCourse (sid,cid) values ( ? , ? )";
		public static final String DECREMENT_COURSE_SEATS = "update CourseCatalogue set filledSeats = filledSeats-1 where cid = ? ";
		
		public static final String DROP_COURSE = "delete from RegisteredCourse where cid = ? AND sid = ?;";
		public static final String INCREMENT_COURSE_SEATS  = "update CourseCatalogue set filledSeats = filledSeats+1 where  cid = ?;";
		
		public static final String VIEW_REGISTERED_COURSES=" select * from CourseCatalogue inner join RegisteredCourse on CourseCatalogue.cid = RegisteredCourse.cid where RegisteredCourse.sid = ?";
		
		public static final String SET_REGISTRATION_STATUS="update Students set isRegistered = true  where sid = ?";

		
	
		
}
