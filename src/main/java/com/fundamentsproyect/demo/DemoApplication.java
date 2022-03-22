package com.fundamentsproyect.demo;

import bean.MyBean;
import bean.MyBeanWithDependency;
import bean.MyBeanWithProperties;
import com.fundamentsproyect.demo.component.ComponentDependency;
import com.fundamentsproyect.demo.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {// inyectado dependencia con spring boot

    private final Log LOGGER = LogFactory.getLog(DemoApplication.class);

    private final ComponentDependency componentDependency; // llamar la interfaz
    private final MyBean myBean;
    private final MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;

    public DemoApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) // constrcutor
    {
        this.myBean = myBean; // al bean de esta clase se le asigna el bean que llega por parametros
        this.componentDependency = componentDependency; // e
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
    }

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        componentDependency.saludar(); // llamado la primera dependencia inyectada
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword() + "-" + userPojo.getAge());
        try{
            //error
            int value = 10/0;
            LOGGER.info("Mi valor : " + value );
        }catch (Exception nombreex){
            LOGGER.error("Esto es un error al dividir por cero :" + nombreex.getMessage());
        }



    }
}
