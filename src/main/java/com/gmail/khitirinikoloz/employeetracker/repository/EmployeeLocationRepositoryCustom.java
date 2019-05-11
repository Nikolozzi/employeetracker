package com.gmail.khitirinikoloz.employeetracker.repository;

import com.gmail.khitirinikoloz.employeetracker.entity.EmployeeLocation;
import com.gmail.khitirinikoloz.employeetracker.entity.dto.EmployeeCount;

import java.util.List;

public interface EmployeeLocationRepositoryCustom {

    List<EmployeeCount> findEmployeeLocationByDateInRange(String startDate, String endDate, int limit);

}
