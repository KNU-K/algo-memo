package com.knuk.algo_memo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AlgoMemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgoMemoApplication.class, args);
    }

}
