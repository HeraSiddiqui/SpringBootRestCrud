package com.example.demo.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	

	private StudentService service;
	
	public StudentController(StudentService service) {
		super();
		this.service = service;
	}

	// build create employee REST API
	@PostMapping()
	public ResponseEntity<Student> saveEmployee(@RequestBody Student student){
		return new ResponseEntity<Student>(service.saveStudent(student), HttpStatus.CREATED);
	}
	
	// build get all employees REST API
	@GetMapping
	public List<Student> getAllStudent(){
		return service.getAllStudent();
	}
	
	// build get student by id REST API
	// http://localhost:8080/api/student/1
	@GetMapping("{id}")
	public ResponseEntity<Student> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Student>(service.getStudentById(employeeId), HttpStatus.OK);
	}
	
	// build update employee REST API
	// http://localhost:8080/api/student/1
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id
												  ,@RequestBody Student student){
		return new ResponseEntity<Student>(service.updateStudent(student, id), HttpStatus.OK);
	}
	
	// build delete Student REST API
	// http://localhost:8080/api/employees/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
		
		// delete Student from DB
		service.deleteStudent(id);
		
		return new ResponseEntity<String>("Student deleted successfully!.", HttpStatus.OK);
	}
	
}
