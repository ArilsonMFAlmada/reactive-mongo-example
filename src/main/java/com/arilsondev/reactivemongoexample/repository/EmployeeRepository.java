package com.arilsondev.reactivemongoexample.repository;

import com.arilsondev.reactivemongoexample.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>{
}
