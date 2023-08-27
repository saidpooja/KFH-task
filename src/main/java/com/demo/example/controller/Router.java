package com.demo.example.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.example.entity.Course;
import com.demo.example.entity.Student;
import com.demo.example.impl.CourseService;
import com.demo.example.impl.StudentService;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_STUDENT')")
public class Router {
	public static final String DEFAULT_ROLE ="ROLE_USER";
	
	@Autowired
	private StudentService studentService;
	// Student API
	
	//while adding user,please enter unique email ID
	@PostMapping("/student/addStudent")
	public String addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}
	
	@GetMapping("/student/getStudents")
	public List<Student> getStudentList() {
		return studentService.getStudentList();
	}
	
	@DeleteMapping("/student/deleteStudent")
	public String deleteStudent(@RequestParam(name = "studentId", required = true) Long studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	@PostMapping("/student/updateCoursesForOneStudent/{studentId}")
	public String updateCoursesForStudent(
			@PathVariable(name = "studentId") Long studentId,
			@RequestBody List<Course> courses) {
		System.out.println("Inside updateCourse:Entry");
		return studentService.updateStudentCourses(studentId, courses);
	}
	
	@GetMapping("/student/getStudentWithCourses")
	public Student getStudentWithCourses(
			@RequestParam(name="studentId", required = true)Long studentId
			) {
		return studentService.getStudentAndCourses(studentId);
	}
	
	//Spring Role Based Security API
	//If logged in student is ADMIN, then he/she can give access to other user
    //If logged in student is student then he/she won't able to give access to user
	@GetMapping("/access/{studentId}/{studentRole}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_STUDENT')")
    public String giveAccessToStudentCopy(@PathVariable Long studentId, @PathVariable String studentRole, Principal principal) {
		return studentService.giveAccessToStudent(studentId, studentRole, principal);
	}
	
	//For Spring security testing purpose, only admin can access this url
	@GetMapping
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String testUserAccess() {
		return "student can only access this !";
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
}
