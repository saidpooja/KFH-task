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

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
}
