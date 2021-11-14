package tn.esprit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimesheetSpringBootCoreDataJpaMvcRest1Application {
	
	public static void main(String[] args) {
		
        //DOMConfigurator is used to configure logger from xml configuration file
        //DOMConfigurator.configure("log4j.xml");

		
		
		SpringApplication.run(TimesheetSpringBootCoreDataJpaMvcRest1Application.class, args);
	}

}
