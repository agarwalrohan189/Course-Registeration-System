/**
 * 
 */
package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Shubham
 *
 */
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig ()
	{
		register(CRSApplicationRestApi.class);
		register(StudentRestAPI.class);
		register(ProfessorRestAPI.class);
		register(AdminRestAPI.class);
	}
	
}
