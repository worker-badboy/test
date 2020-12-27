package com.unluckyworker.appointment.config;

import com.unluckyworker.appointment.interceptor.JWTInterceptor;
import com.unluckyworker.appointment.interceptor.LogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@CrossOrigin(origins = "*", maxAge = 3600)
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/**","/wx/**","/getNext7DaysVisit","/queryDoctorList","/queryDoctorByDepartment");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }

}
