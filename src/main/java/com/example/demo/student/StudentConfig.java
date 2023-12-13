package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student test1 = new Student( "Test Student", LocalDate.of(2000, Month.AUGUST, 5 ), "test1@gmail.com");
            Student test2 = new Student( "Test Student 2", LocalDate.of(2004, Month.AUGUST, 5 ), "test2@gmail.com");

            repository.saveAll(
                List.of(test1, test2)
            );
        };
    }

}
