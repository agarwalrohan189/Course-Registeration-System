package com.dropwizard;

/**
 * Hello world!
 *
 */
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.restController.AdminRestAPI;
import com.flipkart.restController.CRSApplicationRestApi;
import com.flipkart.restController.ProfessorRestAPI;
import com.flipkart.restController.StudentRestAPI;


/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
 
    /*
     * It is a dropwizard intitialize method which will intialize the configuration.
     * */
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
 
    /*
     * Run will execute by the dropwizard container and registered all the web services here.
     * */
    
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
      //  e.jersey().register(new EmployeeRESTController(e.getValidator()));
        e.jersey().register(new CRSApplicationRestApi());
        e.jersey().register(new AdminRestAPI());
        e.jersey().register(new ProfessorRestAPI());
        e.jersey().register(new StudentRestAPI());
    }
 
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}