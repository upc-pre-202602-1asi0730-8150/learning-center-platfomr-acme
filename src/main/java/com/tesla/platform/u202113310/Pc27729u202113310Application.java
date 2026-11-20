package com.tesla.platform.u202113310;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Entry point of the Tesla Autonomous Fleet Management Platform.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@EnableJpaAuditing
@SpringBootApplication
public class Pc27729u202113310Application {

    public static void main(String[] args) {
        SpringApplication.run(Pc27729u202113310Application.class, args);
    }
}
