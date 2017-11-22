package com.carrot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableWebMvc
public class ProviderApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProviderApplication.class,args);
    }
}
