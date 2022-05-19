package com.example.SpringBootMongoDBCRUDApplication.repository;

import com.example.SpringBootMongoDBCRUDApplication.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long>{

}
