package com.yeongjae.damoim;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DamoimApplication {

    public static final String APPLICATION_LOCATIONS =
            "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:aws.yml,"
            + "classpath:secret.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(DamoimApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
        //SpringApplication.run(DamoimApplication.class, args);
    }

}
