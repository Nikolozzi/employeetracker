package com.gmail.khitirinikoloz.employeetracker.repository;

import com.gmail.khitirinikoloz.employeetracker.entity.EmployeeLocation;
import com.gmail.khitirinikoloz.employeetracker.entity.dto.EmployeeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class EmployeeLocationRepositoryCustomImpl implements EmployeeLocationRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<EmployeeCount> findEmployeeLocationByDateInRange(String startDate, String endDate, int limit) {

        Aggregation agg = newAggregation(
            match(Criteria.where("date").gte(startDate).lte(endDate)),
            group("employee").count().as("total"),
            project("total").and("employee").previousOperation(),
            sort(Sort.Direction.DESC,"total"),
            limit(limit)
        );

        AggregationResults<EmployeeCount> groupResults
                = mongoTemplate.aggregate(agg, EmployeeLocation.class, EmployeeCount.class);


        List<EmployeeCount> result = groupResults.getMappedResults();


        return result;
    }
}
