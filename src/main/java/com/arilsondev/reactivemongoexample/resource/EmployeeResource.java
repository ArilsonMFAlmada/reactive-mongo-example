package com.arilsondev.reactivemongoexample.resource;

import com.arilsondev.reactivemongoexample.model.Employee;
import com.arilsondev.reactivemongoexample.model.EmployeeEvent;
import com.arilsondev.reactivemongoexample.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("employees")
public class EmployeeResource {

    private EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Flux<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getId(@PathVariable("id") String id) {
        return employeeService.findById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable("id") String id) {
        return employeeService.findEventsById(id);
    }
}
