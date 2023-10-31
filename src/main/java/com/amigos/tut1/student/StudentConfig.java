package com.amigos.tut1.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student munya=new Student(
                    LocalDate.of(1997, Month.FEBRUARY,26),
                    "munya",
                    "munya@gmail.com"
            );
            Student maita=new Student(
                    LocalDate.of(2012, Month.FEBRUARY,26),
                    "maita",
                    "maita@gmail.com"
            );
            studentRepository.saveAll(List.of(munya,maita));
        };
    }
}
