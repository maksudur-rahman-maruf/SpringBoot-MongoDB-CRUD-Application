package com.example.SpringBootMongoDBCRUDApplication.model;
import java.util.List;

public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String emailId;
    private List<Department> department;

    public EmployeeDto() {
    }

    public EmployeeDto(String firstName, String lastName, String emailId, List<Department> department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }
}
