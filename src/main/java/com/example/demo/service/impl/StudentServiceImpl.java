package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
private StudentRepository repo;
	public StudentServiceImpl(StudentRepository repo) {
	super();
	this.repo = repo;
}

	@Override
	public Student saveStudent(Student student) {
		return repo.save(student);
	}

	@Override
	public List<Student> getAllStudent() {
		return repo.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		return repo.findById(id).orElseThrow(() -> 
						new ResourceAccessException("Data Not Found"));
		
	}

	@Override
	public Student updateStudent(Student student, long id) {
		
		// we need to check whether employee with given id is exist in DB or not
		Student existingStudent = repo.findById(id).orElseThrow(
				() -> new ResourceAccessException("Data Not Found")); 
		
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setCourse(student.getCourse());
		// save existing employee to DB
		repo.save(existingStudent);
		return existingStudent;
	}

	@Override
	public void deleteStudent(long id) {
		
		// check whether a employee exist in a DB or not
		repo.findById(id).orElseThrow(() -> 
								new ResourceAccessException("Data Not Deleted"));
		repo.deleteById(id);
	}

	 
}
	



