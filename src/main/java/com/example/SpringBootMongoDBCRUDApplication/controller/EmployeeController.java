package com.example.SpringBootMongoDBCRUDApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.SpringBootMongoDBCRUDApplication.exception.ResourceNotFoundException;
import com.example.SpringBootMongoDBCRUDApplication.model.Employee;
import com.example.SpringBootMongoDBCRUDApplication.model.EmployeeDto;
import com.example.SpringBootMongoDBCRUDApplication.repository.EmployeeRepository;
import com.example.SpringBootMongoDBCRUDApplication.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@GetMapping("/employees/{emailId}/departments/{head}")
	public ResponseEntity<Employee> getEmployeeByEmailIdAndHead(@PathVariable(value = "emailId") String emailId, @PathVariable(value = "head") String head) {
		Employee employee = employeeRepository.findByEmailIdAndHead(emailId, head);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmailId(employeeDto.getEmailId());
		employee.setDepartment(employeeDto.getDepartment());
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody EmployeeDto employeeDto) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDto.getEmailId());
		employee.setLastName(employeeDto.getLastName());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setDepartment(employeeDto.getDepartment());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
