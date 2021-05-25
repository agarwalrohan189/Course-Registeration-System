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
		
		//ProfessorDao queries
		public static final String GET_PROFESSOR_DETAILS_QUERY = "select * from Professors where id = ?";
		public static final String GET_PROFESSOR_NAME = "select name from Professors where id = ?";
	
		//AdminDao Queries
		public static final String GET_ADMIN_DETAILS_QUERY = "select * from admins where id = ?";
		
		// CourseCatalogue Queries
		public static final String GET_COURSE_CATALOGUE="select * from CourseCatalogue where valid = true";
		public static final String GET_COURSE_NAME="select cname from CourseCatalogue where cid = ?";
		
		//RegisteredCourse Queries
		public static final String GET_REGISTERED_COURSE_DETAILS="select * from RegistedCourse where sid = ?";
		
		//AdminDao Queries
		public static final String DELETE_COURSE_QUERY = "delete from CourseCatalogue where cid = ?";
		public static final String ADD_COURSE_QUERY = "insert into CourseCatalogue(cid, cname, pid, valid) values (?, ?, ?, ?)";
		public static final String VIEW_PENDING_ADMISSION_QUERY = "select userId, name, password, role, gender, address, country, studentId from student natural join user where isApproved = 0";
		public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = 1 where studentId = ?";
		public static final String ADD_USER_QUERY = "insert into Users(id, password, name, gender, role, address, username, dob) values (?, ?, ?, ?, ?, ?, ?, ?)";
		public static final String ADD_PROFESSOR_QUERY = "insert into Professors(id, department, qualification, doj) values (?, ?, ?, ?)";
		public static final String DELETE_PROFESSOR_QUERY = "delete from Professors where id = ?";
		public static final String ASSIGN_COURSE_QUERY = "update CourseCatalogue set pid = ? where cid = ?";
		public static final String VIEW_COURSE_QUERY = "select courseCode, courseName, professorId from Course where catalogId = ?";
		public static final String VIEW_PROFESSOR_QUERY = "select userId, name, gender, department, designation, address, country from Professor natural join User";
		
		public static final String ADD_STUDENT="insert into Students (id,branch,batchYear,paymentIsDone) values (?,?,?,?)";
		public static final String DELETE_STUDENT_QUERY = "delete from Students where id = ?";
		public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
		public static final String GET_ROLE="select role from user where userId = ? ";
		public static final String IS_APPROVED="select isApproved from student where studentId = ? ";
		public static final String GET_STUDENT_ID="select studentId from student where userId = ? ";
		public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
		public static final String GET_PROF_NAME = "select name from user where userId = ?";
		
		public static final String DROP_COURSE_QUERY = "delete from registeredcourse where courseCode = ? AND studentId = ?;";
		
		// Student Queries
		public static final String VIEW_AVAILABLE_COURSES=" select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId = ?) and course.isOffered = ? and seats > 0";
		public static final String CHECK_COURSE_AVAILABILITY=" select courseCode from registeredcourse where studentId = ? ";
		
		//Registration
		public static final String ADD_COURSE = "insert into RegisteredCourse (sid,cid) values ( ? , ? )";
		public static final String DECREMENT_COURSE_SEATS = "update CourseCatalogue set filledSeats = filledSeats-1 where cid = ? ";
		
		public static final String DROP_COURSE = "delete from RegisteredCourse where cid = ? AND sid = ?;";
		public static final String INCREMENT_COURSE_SEATS  = "update CourseCatalogue set filledSeats = filledSeats+1 where  cid = ?;";
		
		public static final String VIEW_REGISTERED_COURSES=" select * from CourseCatalogue inner join RegisteredCourse on CourseCatalogue.cid = RegisteredCourse.cid where RegisteredCourse.sid = ?";
		
		public static final String SET_REGISTRATION_STATUS="update Students set isRegistered = true  where sid = ?";

		public static final String CALCULATE_FEES  = "select sum(courseFee) from course where courseCode in (select courseCode from registeredcourse where studentId = ?);";
		public static final String VIEW_GRADE = "select course.courseCode,course.courseName,registeredcourse.grade from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?;";	
		public static final String GET_SEATS = "select seats from course where courseCode = ?;";
		public static final String INSERT_PAYMENT = "insert into payment(studentId,modeofPayment,referenceId,amount) values(?,?,?,?);";
		public static final String INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
		public static final String GET_NOTIFICATION = "select * from notification where referenceId = ?;";
		public static final String ADD_GRADE="update registeredcourse set Grade=? where courseCode=? and studentId=?";
		public static final String GET_COURSES="select * from course where professorId=?";
		public static final String GET_REGISTRATION_STATUS=" select isRegistered from student where studentId = ? ";
		public static final String GET_ENROLLED_STUDENTS="select course.courseCode,course.courseName,registeredcourse.studentId from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where course.professorId = ? order by course.courseCode";
		public static final String NUMBER_OF_REGISTERED_COURSES=" select studentId from registeredcourse where studentId = ? ";
		public static final String IS_REGISTERED=" select courseCode from registeredcourse where courseCode=? and studentId=? ";
		public static final String GET_STUDENT_DETAILS_QUERY = "select * from Students where id = ?";
		public static final String GET_STUDENT_GRADES = "select grade, courseID from registeredCourse where studentID=?";
}
