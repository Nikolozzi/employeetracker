package com.gmail.khitirinikoloz.employeetracker.mongodb;

import com.gmail.khitirinikoloz.employeetracker.entity.EmployeeLocation;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLocationRepository extends MongoRepository<EmployeeLocation,String> {

    List<EmployeeLocation> findByLocationNear(Point p, Distance d);
    void deleteByEmployee(String email);

}
