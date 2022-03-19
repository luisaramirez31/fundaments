package com.fundamentsproyect.demo;

import bean.MyBean;
import com.fundamentsproyect.demo.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner

{// inyectado dependencia con spring boot
	private ComponentDependency componentDependency; // llamar la interfaz
	private MyBean myBean;


	public DemoApplication (@Qualifier("componentTwoImplement")ComponentDependency componentDependency, MyBean  a) // constrcutor
	{
		this.myBean = a ; // al bean de esta clase se le asigna el bean que llega por parametros
		this.componentDependency = componentDependency; // e

	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar(); // llamado la primera dependencia inyectada
		myBean.print();
	}
}
