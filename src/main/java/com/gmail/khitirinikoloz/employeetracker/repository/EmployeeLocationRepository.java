package com.gmail.khitirinikoloz.employeetracker.repository;

import com.gmail.khitirinikoloz.employeetracker.entity.EmployeeLocation;
import com.gmail.khitirinikoloz.employeetracker.entity.dto.EmployeeCount;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLocationRepository extends MongoRepository<EmployeeLocation,String>, EmployeeLocationRepositoryCustom {

    List<EmployeeLocation> findByLocationNear(Point p, Distance d);
    void deleteByEmployee(String email);

    @Override
    List<EmployeeCount> findEmployeeLocationByDateInRange(String startDate, String endDate, int limit);
}
