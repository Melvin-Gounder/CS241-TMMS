package fj.usp.tmms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TmmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TmmsApplication.class, args);
    }
}
