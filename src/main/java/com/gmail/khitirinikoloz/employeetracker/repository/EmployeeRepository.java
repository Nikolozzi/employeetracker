package com.gmail.khitirinikoloz.employeetracker.repository;

import com.gmail.khitirinikoloz.employeetracker.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

    List<Employee> findByFirstNameIgnoreCase(String firstName);
    Employee findByEmail(String email);
    void deleteByEmail(String email);
}