package com.gmail.khitirinikoloz.employeetracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;


@Document(collection = "locations")
public class EmployeeLocation {

    @Id
    private String Id;

    private String employee; //employee email
    private GeoJsonPoint location;
    private String date;

    public EmployeeLocation() {}

    public EmployeeLocation(String employee, GeoJsonPoint location) {
        this.employee = employee;
        this.location = location;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        this.date = formatter.format(new Date(System.currentTimeMillis()));
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }
}
