/**
 * 
 */
package com.flipkart.restController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.bean.Student;
import com.flipkart.constant.RoleConstant;
import com.flipkart.exception.StudentNotFoundException;
import com.flipkart.service.UserOperation;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.StudentInterface;

/**
 * @author Shubham
 *
 */

@Path("/users")
public class CRSApplicationRestApi {

	StudentInterface studentInterface = StudentOperation.getInstance();
	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login (
			@NotNull
			@QueryParam("userId") String userId,
			@NotNull
			@QueryParam("password") String password)
	{
		RoleConstant role = null;
		try {
			role = UserOperation.getInstance().login(userId, password);
		} catch (Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
		finally
		{
			if (role == RoleConstant.Student)
			{
				try {
					if (studentInterface.isApproved(userId)) {
						return Response.status(200).entity("Login successful! student id = "+userId ).build();
					} else {
						return Response.status(500).entity("Login failed. Waiting for approval").build();
					}
				} catch (StudentNotFoundException e) {
					return Response.status(500).entity(e.getMessage()).build();
				}
			}
			else if (role == RoleConstant.Professor)
			{
				return Response.status(200).entity("Login successful! professor id = "+userId ).build();
			}
			else if (role == RoleConstant.Admin)
			{
				return Response.status(200).entity("Login unsuccessful! admin id = "+userId ).build();
			}
		}
		return Response.status(500).entity("Login failed").build();
	}
	
	/**
	 * Method for student to signup
	 */
	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(@Valid Student student) {
		try {

			studentInterface.signUp(student);

		}catch (Exception e){
			return Response.status(500).entity(e.getMessage()).build();
		}
		return Response.status(201).entity("Registration Successful for: "+student.getId()).build();
	}
	
}
