package com.gmail.khitirinikoloz.employeetracker.entity.dto;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class EmployeeCount {

    private String employee;
    private long total;


    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
