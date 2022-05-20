package com.example.SpringBootMongoDBCRUDApplication.repository;

import com.example.SpringBootMongoDBCRUDApplication.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long>{

    public Employee findByEmailIdAndDepartmentHead(String id, String head);
//    Another Way ->
//    @Query("{ 'emailId': ?0, 'department.head': ?1}")
//    public Employee findByEmailIdAndHead(String id, String head);

}
