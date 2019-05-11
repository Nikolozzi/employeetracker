package com.gmail.khitirinikoloz.employeetracker.rest.exceptionhandling;

import com.gmail.khitirinikoloz.employeetracker.entity.Employee;
import com.gmail.khitirinikoloz.employeetracker.entity.dto.LocationDto;
import com.gmail.khitirinikoloz.employeetracker.repository.EmployeeRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeVerification {

    public static void verifyLocation(LocationDto location, EmployeeRepository repository){

        if(location.getEmployee() == null || location.getEmployee().isEmpty() ||
                location.getLongitude() == null || location.getLongitude().isEmpty() ||
                location.getLatitude() == null || location.getLatitude().isEmpty()){
            throw new InvalidEmployeeException("Empty fields are not allowed");
        }

        if(repository.findByEmail(location.getEmployee()) == null){
            throw new InvalidEmployeeException("Could not save location - such user does not exist");
        }
    }

    public static void verify(Employee employee, EmployeeRepository repository){
        if (employee.getFirstName() == null || employee.getLastName() == null || employee.getEmail() == null || employee.getPosition() == null) {
            throw new InvalidEmployeeException("Empty fields are not allowed");
        }

        verifyFirstName(employee.getFirstName());
        verifyLastName(employee.getLastName());
        verifyEmailAddress(employee.getEmail(), repository);
        verifyPositionName(employee.getPosition());
    }

    private static void verifyFirstName(String firstName) {

        String pattern = "^[a-zA-Z]{2,30}$";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(firstName);

        if (!matcher.find()) {
            throw new InvalidEmployeeException("First name is not valid");
        }
    }

    private static void verifyLastName(String lastName) {

        String pattern = "^[a-zA-Z]{2,60}$";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(lastName);

        if (!matcher.find()) {
            throw new InvalidEmployeeException("Last name is not valid");
        }
    }

    private static void verifyEmailAddress(String emailAddress, EmployeeRepository repository){

        //not accurate, but works in most cases
        String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern r = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
        Matcher matcher = r.matcher(emailAddress);

        if(!matcher.find()){
            throw new InvalidEmployeeException("Email address may not be valid");
        }

        if(repository.findByEmail(emailAddress) != null){
            throw new InvalidEmployeeException("Email address already exists");
        }
    }

    private static void verifyPositionName(String position){
        position = position.trim();
        String pattern = "^[a-zA-Z ]{2,100}$";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(position);

        if(!matcher.find()){
            throw new InvalidEmployeeException("Position name is not valid");
        }
    }
}
