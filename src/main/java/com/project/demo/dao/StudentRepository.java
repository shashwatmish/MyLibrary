package com.project.demo.dao;

import java.util.List;

import com.project.demo.models.Student;

public interface StudentRepository {
	Student saveStudent(Student student);
	Student getStudentById(int id);
	List<Student> getAllStudents();
	int updateStudent(Student student);
	int deleteStudentById(int id);
}
