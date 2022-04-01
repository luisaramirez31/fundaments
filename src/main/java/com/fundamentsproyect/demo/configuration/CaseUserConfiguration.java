package com.fundamentsproyect.demo.configuration;

import com.fundamentsproyect.demo.caseuse.GetUser;
import com.fundamentsproyect.demo.caseuse.GetUserImplement;
import com.fundamentsproyect.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUserConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
