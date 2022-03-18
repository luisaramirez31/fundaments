package com.fundamentsproyect.demo;

import com.fundamentsproyect.demo.bean.MyBean;
import com.fundamentsproyect.demo.bean.MyBeanWithDependency;
import com.fundamentsproyect.demo.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	public DemoApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency)
	{
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
	}


	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception
	{
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
	}
}
