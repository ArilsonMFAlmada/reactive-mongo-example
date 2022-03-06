package com.arilsondev.reactivemongoexample.service.impl;

import com.arilsondev.reactivemongoexample.model.Employee;
import com.arilsondev.reactivemongoexample.model.EmployeeEvent;
import com.arilsondev.reactivemongoexample.repository.EmployeeRepository;
import com.arilsondev.reactivemongoexample.service.EmployeeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Flux<Employee> findAll() {
        return employeeRepository
                .findAll();
    }

    @Override
    public Mono<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Flux<EmployeeEvent> findEventsById(String id) {
       return employeeRepository.findById(id)
                .flatMapMany(employee -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

                    Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(
                            Stream.generate(() -> new EmployeeEvent(employee, new Date()))
                    );

                    return Flux.zip(interval, employeeEventFlux)
                            .map(Tuple2::getT2);
                });
    }
}


