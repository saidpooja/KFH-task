package com.demo.example.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Teacher {
	
//	@Id
//	@GeneratedValue
	private int teacherId;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNo;
	private String degree;
	private String role="ROLE_ADMIN";
	private List<Course> courses;
	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public Teacher() {
		super();
	}
	
	public Teacher(int teacherId, String firstName, String lastName, String password, String phoneNo, String degree,
			String role, List<Course> courses) {
		super();
		this.teacherId = teacherId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.phoneNo = phoneNo;
		this.degree = degree;
		this.role = role;
		this.courses = courses;
	}
	
	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", phoneNo=" + phoneNo + ", degree=" + degree + ", role=" + role + ", courses=" + courses
				+ "]";
	}	
}
