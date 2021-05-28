/**
 * 
 */
package com.flipkart.restController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.GradeConstant;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.service.AdminOperation;
import com.flipkart.service.RegistrationOperation;

/**
 * @author Shubham
 *
 */

@Path("/admin")
public class AdminRestAPI {
	/**
	 * /admin/viewCourseCatalogue
	 * REST-service for getting courses in the catalog
	 * @return
	 */
	@GET
	@Path("/viewCourseCatalogue")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewCourseCatalogue() {
		
		return AdminOperation.getInstance().viewCourses();
		
	}

	/**
	 * /admin/addCourse
	 * REST-service for adding a new course in catalog
	 * @param course
	 * @return
	 */
	@POST
	@Path("/addCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@Valid Course course) throws ValidationException{
		
		try {
			
			AdminOperation.getInstance().addCourse(course);
			return Response.status(201).entity("Course with courseCode: " + course.getCourseId() + " added to catalog").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
			
	}

	/**
	 * /admin/removeCourse
	 * REST-services for dropping a course from catalog
	 * @param courseId
	 * @return
	 */
	@DELETE
	@Path("/removeCourse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeCourse(
			// @Size(min = 4 , max = 10 , message = "Course Code length should be between 4 and 10 character") //TODO: Check if this is necessary
			@NotNull
			@QueryParam("courseId") Integer courseId) throws ValidationException{
		
		try {
			
			AdminOperation.getInstance().removeCourse(courseId);
			return Response.status(201).entity("Course with courseCode: " + courseId + " deleted from catalog").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}

	/**
	 * /admin/addProf
	 * REST-service for addding a new professor
	 * @param professor
	 * @return
	 */
	@POST
	@Path("/addProf")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProf(@Valid Professor professor) throws ValidationException{
		 
		try {
			
			AdminOperation.getInstance().addProf(professor);
			return Response.status(201).entity("Professor with professorId: " + professor.getId() + " added").build();
			
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
			
		}
		
	}

	@DELETE
	@Path("/removeProf")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProf(
			@NotNull
			@QueryParam("pid") String pid) throws ValidationException{
		
		try {
			AdminOperation.getInstance().removeProf(pid);
			return Response.status(201).entity("Prof with profId: " + pid + " has been removed").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}

	/**
	 * /admin/approveStudent
	 * REST-service for approving the student admission
	 * @param studentId
	 * @return
	 */
	@PUT
	@Path("/approveStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudent(
			// @Min(value = 1, message = "Student ID should not be less than 1")
			// @Max(value = 9999, message = "Student ID should be less than 10000") //TODO: Check if this is necessary
			@NotNull
			@QueryParam("sid") String sid) throws ValidationException{
		
		try {
			
			AdminOperation.getInstance().approveStudent(sid);
			return Response.status(201).entity("Student with studentId: " + sid + " approved").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
		
	}


	@DELETE
	@Path("/removeStudent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeStudent(
			@NotNull
			@QueryParam("sid") String sid) throws ValidationException{
		
		try {
			AdminOperation.getInstance().removeStudent(sid);
			return Response.status(201).entity("Student with studentId: " + sid + " has been removed").build();
		
		} catch (Exception e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}

	@PUT
	@Path("/assignProf")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignProf(
			// @Size(min = 4 , max = 10 , message = "courseCode length should be between 4 and 10 character")
			@NotNull
			@QueryParam("courseId") int courseId, 
			// @Email(message = "Invalid Professor ID: Not in email format") //TODO: idk what this is
			@NotNull
			@QueryParam("profId") String profId) throws ValidationException {
		
			try {
				
				AdminOperation.getInstance().assignProf(profId, courseId);
				return Response.status(201).entity("courseCode: " + courseId + " assigned to professor: " + profId).build();
				
			} catch (Exception e) {
				
				return Response.status(409).entity(e.getMessage()).build();
				
			}
	}

	/**
	 * Method handles request to display the grade card for student
	 * @param sid
	 * @return
	 * @throws ValidationException
	 */
	
	@GET
	@Path("/generateReport")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GradeConstant> generateReport(
			@NotNull
			// @Min(value = 1, message = "Student ID should not be less than 1")
			// @Max(value = 9999, message = "Student ID should be less than 1000")
			@QueryParam("sid") String sid) throws ValidationException{

			List<GradeConstant> grades = new ArrayList<GradeConstant>();
			List<RegisteredCourse> courses = null;
			try {
				courses = RegistrationOperation.getInstance().viewRegisteredCourses(sid);
			} catch (StudentNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			for (RegisteredCourse course : courses) {
				grades.add(course.getGrade());
			}
			return grades;
	}



	/**
	 * /admin/validateRegistrations
	 * REST-service for validating the courses in the catalogue
	 * @return
	 */
	@GET
	@Path("/validateRegistrations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateRegistrations() {

		try {
			AdminOperation.getInstance().validateRegistration();
			return Response.status(200).entity(":) :) :) registrations Validated :) :) :)").build();
		} catch (Exception e) {
			return Response.status(409).entity(e.getMessage()).build();
		}
		
	}

	@PUT
	@Path("/confirmFeePayment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmFeePayment(

			@NotNull
			@QueryParam("modeOfPayment") Integer modeOfPayment, 

			@NotNull
			@QueryParam("sid") String sid) throws ValidationException {
		
			try {
				
				switch(modeOfPayment) {
					case 1:
						AdminOperation.getInstance().paymentDoneViaScholarship(sid);
						return Response.status(201).entity("Payment done for student with id " + sid + " via Scholarship").build();
					case 2:
						AdminOperation.getInstance().paymentDoneViaDemandDraft(sid);
						return Response.status(201).entity("Payment done for student with id " + sid + " via Demand Draft").build();
					default:
						System.out.println("Confirmation Of Payment Cancelled.");
						return Response.status(201).entity("Confirmation Of Payment Cancelled.").build();
				}
				
			} catch (Exception e) {
				
				return Response.status(409).entity(e.getMessage()).build();
				
			}
	}


}
