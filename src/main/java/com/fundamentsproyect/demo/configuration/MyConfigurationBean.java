package com.fundamentsproyect.demo.configuration;

import bean.MyBean;
import bean.MyBeanImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class MyConfigurationBean {

    @Bean
    public MyBean beanOperation(){

        return new MyBeanImplement();
    }

}
