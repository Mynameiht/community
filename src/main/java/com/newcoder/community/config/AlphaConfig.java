package com.newcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AlphaConfig {

    @Bean
    public SimpleDateFormat getSdf(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
