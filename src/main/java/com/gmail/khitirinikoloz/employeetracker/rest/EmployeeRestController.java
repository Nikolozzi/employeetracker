package com.gmail.khitirinikoloz.employeetracker.rest;

import com.gmail.khitirinikoloz.employeetracker.entity.Employee;
import com.gmail.khitirinikoloz.employeetracker.entity.EmployeeLocation;
import com.gmail.khitirinikoloz.employeetracker.entity.LocationEntry;
import com.gmail.khitirinikoloz.employeetracker.mongodb.EmployeeLocationRepository;
import com.gmail.khitirinikoloz.employeetracker.mongodb.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private EmployeeLocationRepository locationRepo;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {

        List<Employee> employees = employeeRepo.findAll();

        if(employees.isEmpty()){
            throw new InvalidEmployeeException("No employees were found");
        }

        return employees;
    }

    @GetMapping("/employees/{firstName}")
    public List<Employee> getEmployeesByName(@PathVariable String firstName) {

        List<Employee> employees = employeeRepo.findByFirstNameIgnoreCase(firstName);

        if (employees.isEmpty()) {
            throw new InvalidEmployeeException("No employee was found with this name");
        }

        return employees;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        EmployeeVerification.verify(employee,employeeRepo);
        employeeRepo.insert(employee);

        return employee;
    }

    @PostMapping("/employees/location")
    public EmployeeLocation addEmployeeLocation(
            @RequestBody LocationEntry location)
    {
        EmployeeVerification.verifyLocation(location, employeeRepo);

        GeoJsonPoint locationPoint = new GeoJsonPoint(Double.valueOf(location.getLongitude()),
                                Double.valueOf(location.getLatitude()));

        EmployeeLocation employeeLocation = new EmployeeLocation(location.getEmployee(),locationPoint);

        return locationRepo.insert(employeeLocation);
    }

    @GetMapping("/employees/location")
    public List<EmployeeLocation> getEmployeeByLocation(
            @RequestParam("lon") String longitude,
            @RequestParam("lat") String latitude, @RequestParam(value = "d",required = false, defaultValue = "0") String distance)
    {

        Point point = new Point(Double.valueOf(longitude),Double.valueOf(latitude));
        Distance dist = new Distance(Double.valueOf(distance), Metrics.KILOMETERS);

        List<EmployeeLocation> employees = locationRepo.findByLocationNear(point,dist);

        if(employees.isEmpty()){
            throw new InvalidEmployeeException("No employees were located in the area");
        }

        return employees;
    }

    @DeleteMapping("/employees")
    public String deleteEmployee(@RequestParam("email") String email){

        Employee employee = employeeRepo.findByEmail(email);

        if(employee == null){
            throw new InvalidEmployeeException("Could not delete - no such employee");
        }

        employeeRepo.deleteByEmail(email);
        locationRepo.deleteByEmployee(email);

        return "Deleted user: " + email;
    }
}
