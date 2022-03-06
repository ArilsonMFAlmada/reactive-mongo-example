package com.arilsondev.reactivemongoexample;

import com.arilsondev.reactivemongoexample.model.Employee;
import com.arilsondev.reactivemongoexample.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoExampleApplication.class, args);
    }

    @Bean
    CommandLineRunner employees(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.deleteAll()
                    .subscribe(null, null, () -> {
                        Stream.of(Employee.builder()
                                .id(UUID.randomUUID().toString())
                                .name("John")
                                .salary(2300L)
                                .build()).forEach(employee -> {
                            employeeRepository.save(employee)
                                    .subscribe(System.out::println);
                        });

                    });
        };
    }
}
