package com.arilsondev.reactivemongoexample.service;

import com.arilsondev.reactivemongoexample.model.Employee;
import com.arilsondev.reactivemongoexample.model.EmployeeEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface EmployeeService {

    Flux<Employee> findAll();

    Mono<Employee> findById(String id);

    Flux<EmployeeEvent> findEventsById(String id);
}
