package com.project.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository
{
	private static final String saveStudent="INSERT INTO STUDENT (fname,lname,password,age,contact,email,street,area,pincode,booksissued,gender) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String getStudentById ="SELECT * FROM STUDENT WHERE STUDENTID=? ";
	private static final String getAllStudents = "SELECT * FROM STUDENT";
	private static final String UpdateStudent = "UPDATE STUDENT SET fname=?,lname=?,password=?,age=?,contact=?,email=?,street=?,area=?,pincode=?,gender=?,booksissued=? WHERE STUDENTID=?";
	private static final String UpdateStudentNew = "UPDATE STUDENT SET fname=?,lname=?,password=?,age=?,contact=?,email=?,street=?,area=?,pincode=?,gender=? WHERE STUDENTID=?";
	private static final String deleteStudentById = "DELETE FROM STUDENT WHERE STUDENTID=?";
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Override
	public int saveStudent(Student student) 
	{
		return jdbctemplate.update(saveStudent,student.getFname(),student.getLname(),student.getPassword(),student.getAge(),student.getContact(),student.getEmail(),student.getStreet(),student.getArea(),student.getPincode(),student.getBooksissued(),student.getGender());
		
	}

	@Override
	public Student getStudentById(int id) {
		try {
			return jdbctemplate.queryForObject(getStudentById,(rs,rowNum)->{
				Student student = new Student();
				student.setStudentid(rs.getInt(1));
				student.setFname(rs.getString(2));
				student.setLname(rs.getString(3));
				student.setPassword(rs.getString(4));
				student.setAge(rs.getInt(5));
				student.setGender(rs.getString(6));
				student.setContact(rs.getString(7));
				student.setEmail(rs.getString(8));
				student.setStreet(rs.getString(9));
				student.setArea(rs.getString(10));
				student.setPincode(rs.getLong(11));
				student.setBooksissued(rs.getInt(12));
				return student;
			},id);
		}
		catch(Exception e) {
			Student student = new Student();
			student.setStudentid(-1);
			return student;
		}
	}

	@Override
	public List<Student> getAllStudents() {
		
		return jdbctemplate.query(getAllStudents,(rs,rowNum)->{
			Student student = new Student();
			student.setStudentid(rs.getInt(1));
			student.setFname(rs.getString(2));
			student.setLname(rs.getString(3));
			student.setPassword("CAN'T DISPLAY, SORRY!!!");
			student.setAge(rs.getInt(5));
			student.setGender(rs.getString(6));
			student.setContact(rs.getString(7));
			student.setEmail(rs.getString(8));
			student.setStreet(rs.getString(9));
			student.setArea(rs.getString(10));
			student.setPincode(rs.getLong(11));
			student.setBooksissued(rs.getInt(12));
			return student;
		});
	}
	
	@Override
	public int updateStudent(Student student) {
		return jdbctemplate.update(UpdateStudent,
				student.getFname(),
				student.getLname(),
				student.getPassword(),
				student.getAge(),
				student.getContact(),
				student.getEmail(),
				student.getStreet(),
				student.getArea(),
				student.getPincode(),
				student.getGender(),
				student.getBooksissued(),
				student.getStudentid());
	}

	@Override
	public int deleteStudentById(int id) {
		return jdbctemplate.update(deleteStudentById,id);
	}

	@Override
	public int updateStudentNew(Student student) {
		return jdbctemplate.update(UpdateStudentNew,
				student.getFname(),
				student.getLname(),
				student.getPassword(),
				student.getAge(),
				student.getContact(),
				student.getEmail(),
				student.getStreet(),
				student.getArea(),
				student.getPincode(),
				student.getGender(),
				student.getStudentid());
	}

}
