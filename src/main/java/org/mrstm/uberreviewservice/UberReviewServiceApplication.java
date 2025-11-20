package org.mrstm.uberreviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("org.mrstm.uberentityservice.models")
@EnableDiscoveryClient
@EnableScheduling
public class UberReviewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberReviewServiceApplication.class, args);
    }

}
