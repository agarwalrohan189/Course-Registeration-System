/**
 * 
 */
package com.flipkart.restController;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
 
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
import org.apache.log4j.Logger;
 
import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.ModeOfPaymentConstant;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.CourseSeatsFullException;
import com.flipkart.exception.DatabaseException;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.service.NotificationOperation;
import com.flipkart.service.RegistrationInterface;
import com.flipkart.service.RegistrationOperation;
import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
 
/**
 * @author sayan
 *
 */
@Path("/student")
public class StudentRestAPI {
	private static Logger logger = Logger.getLogger(StudentRestAPI.class);
	StudentInterface studentInterface = StudentOperation.getInstance();
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
	
	@GET
	@Path("/viewCourseCatalogue")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourseCatalogue(){
		List<Course> lcourse = new ArrayList<Course>();
		try {
			lcourse = studentInterface.viewCourseCatalogue();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lcourse;
	}
	
	@GET
	@Path("/viewGrades/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RegisteredCourse> viewGrades(@PathParam("studentID") String studentID) {
		List<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();
		try {
			 courses = studentInterface.viewGrades(studentID);
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}
	
	@GET
	@Path("/viewRegisteredCourses/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RegisteredCourse> viewRegisteredCourses(@PathParam("studentID") String studentID) {
		List<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();
		try {
			courses = registrationInterface.viewRegisteredCourses(studentID);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}
	
	@GET
	@Path("/showNotifications/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Notification> showNotifications(@PathParam("studentID") String studentID)
	{
		List<Notification> notifications = NotificationOperation.getInstance().getNotifications(studentID);
		return notifications;
	}
	
	@PUT
	@Path("/addCourse/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@PathParam("studentID") String studentID, @QueryParam("courseID") int courseID) {
		try {
			registrationInterface.addCourse(studentID, courseID);
			return Response.status(201).entity( "Course with courseID " + courseID + " has been added.").build();
 
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
 
		}
	}
	
	
	@DELETE
	@Path("/dropCourse/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(@PathParam("studentID") String studentID, @QueryParam("courseID")  int courseID) {
		try {
			registrationInterface.dropCourse(studentID, courseID);
			return Response.status(200).entity( "Course with courseID " + courseID + " has been dropped.").build();
 
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
 
		}
	}
	
	
	@POST
	@Path("/registerCourses/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourses(@PathParam("studentID") String studentID,@QueryParam("p1") int p1, @QueryParam("p2") int p2, @QueryParam("p3") int p3, @QueryParam("p4") int p4, @QueryParam("a1") int a1, @QueryParam("a2") int a2) {
		HashMap<Integer,Boolean> courseIDs = new HashMap<>();
		courseIDs.put(p1, true);
		courseIDs.put(p2, true);
		courseIDs.put(p3, true);
		courseIDs.put(p4, true);
		courseIDs.put(a1, false);
		courseIDs.put(a2, false);
		try {
			registrationInterface.registerCourses(studentID, courseIDs);
			return Response.status(202).entity( "Registration Done.").build();
 
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
		}
 
	}
	
	@PUT
	@Path("/payFee/{studentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response payFee(@PathParam("studentID") String studentID, @QueryParam("mode") String mode) {
		try{
			if(!registrationInterface.isRegistrationDone(studentID)) {
				logger.info("Registration not yet complete.");
				return Response.status(200).entity( "Registration not yet complete.").build();
			}else if(registrationInterface.isPaymentDone(studentID)) {
				logger.info("Payment already done.");
				return Response.status(200).entity( "Payment already done.").build();
 
			}else {
				float amount = registrationInterface.calculateFee(studentID);
				registrationInterface.payFee(studentID, ModeOfPaymentConstant.valueOf(mode), amount);
				return Response.status(202).entity( "Payment successful.").build();
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	
}