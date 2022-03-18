package com.fundamentsproyect.demo;

import com.fundamentsproyect.demo.component.ComponentDependency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {
	private ComponentDependency componentDependency;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
