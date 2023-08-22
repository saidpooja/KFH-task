package com.demo.example.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(name = "emailid_unique", columnNames = "email_address"))
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Student {

	@Id
	@GeneratedValue
	private Long studentId;
	private String firstName;
	private String lastName;
	private String address;
	private String telephoneNumber;
	private String password;
	private boolean isActive=true;
	private String roles;
	
	@OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", referencedColumnName = "studentId")
	private List<Course> courses;

	@Column(name = "email_address", nullable = false)
	private String emailId;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Student() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Student(String firstName, String lastName, String address, String telephoneNumber, String password,
			boolean isActive, String roles, List<Course> courses, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
		this.isActive = isActive;
		this.roles = roles;
		this.courses = courses;
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", telephoneNumber=" + telephoneNumber + ", password=" + password + ", isActive=" + isActive
				+ ", roles=" + roles + ", courses=" + courses + ", emailId=" + emailId + "]";
	}
}
