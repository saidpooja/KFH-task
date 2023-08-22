package com.demo.example.impl;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.demo.example.constant.UserConstant;
import com.demo.example.entity.Course;
import com.demo.example.entity.Student;
import com.demo.example.repository.CourseRepository;
import com.demo.example.repository.StudentRepository;

@Service
public class StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Modifying
	public String updateStudentCourses(Long studentId, List<Course> newCourses) {
		logger.info("Inside StudentService.updateStudentCourses: Entry");
		Student student = studentRepository.findById(studentId).orElse(null);

		String outputString = "Please enter the valid course name";
		int updatedRowCount = 0;
		if (student != null) {
			List<Course> existingCourses = student.getCourses();
			// Update the course
			for (int i = 0; i < newCourses.size(); i++) {
				for (int j = 0; j < existingCourses.size(); j++) {
					if (existingCourses.get(j).getTitle().equalsIgnoreCase(newCourses.get(i).getTitle())) {
						updatedRowCount = courseRepository.updateCourseCreditByCourseName(newCourses.get(i).getCredit(),
								newCourses.get(i).getTitle());
					}
				}
				if (updatedRowCount > 0) {
					outputString = "Courses updated successfully";
				}
			}
		}
		logger.info("Inside StudentService.updateStudentCourses: Exit");
		return outputString;
	}

	public void updateRoleByStudentId(String role, Long studentId) {
		logger.info("Inside StudentService.updateRoleByStudentId: Entry");
		studentRepository.updateRoleByStudentId(role, studentId);
		logger.info("Inside StudentService.updateRoleByStudentId: Exit");
	}

	public List<Student> getStudentList() {
		logger.info("Inside StudentService.getStudentList: Entry");
		List<Student> studentObj = studentRepository.findAll();
		logger.info("Inside StudentService.getStudentList: Exit");
		return studentObj;
	}

	public String deleteStudent(Long studentId) {
		logger.info("Inside StudentService.deleteCourse:Entry");
		studentRepository.deleteById(studentId);
		logger.info("Inside StudentService.deleteCourse:Exit");
		return "Student deleted successfully";
	}

	public Student getStudentAndCourses(Long studentId) {
		logger.info("Inside StudentService.getStudentAndCourses:Entry");
		String jpql = "SELECT s FROM Student s JOIN s.courses c WHERE s.studentId = :studentId";
		TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
		query.setParameter("studentId", studentId);
		logger.info("Inside StudentService.getStudentAndCourses:Exit");
		return query.getSingleResult();
	}

	public String addStudent(Student student) {
		String output = "";
		logger.info("Inside StudentService.addCourse:Entry");
		student.setRoles(UserConstant.DEFAULT_ROLE);
		String encryptedPassword = passwordEncoder.encode(student.getPassword());
		student.setPassword(encryptedPassword);
		Student studentObj = studentRepository.save(student);
		if (studentObj != null) {
			output = "Student added successfully";
		} else {
			output = "Please try with valid details";
		}
		logger.info("Inside StudentService.addCourse:Exit");
		return output;
	}

	public String giveAccessToStudent(Long studentId, String studentRole, Principal principal) {
		logger.info("Inside StudentService.giveAccessToStudent:Entry");
		String outputMessage = "";
		Student student = studentRepository.findById(studentId).get();
		List<String> activeRoles = getRolesByLoggedInStudent(principal);
		logger.info("activeRoles: " + activeRoles + ", studentRole: " + studentRole);
		String newRole = studentRole;
		updateRoleByStudentId(newRole, studentId);
		outputMessage = "Hi " + student.getFirstName() + " New Role assign to you by " + principal.getName();
		logger.info("Inside StudentService.giveAccessToStudent:Exit");
		return outputMessage;
	}

	private List<String> getRolesByLoggedInStudent(Principal principal) {
		logger.info("Inside StudentService.getRolesByLoggedInStudent:Entry");
		String roles = getLoggedInUser(principal).getRoles();
		logger.info("getRolesByLoggedInStudent: Entry: " + roles);
		List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if (assignRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
		}
		if (assignRoles.contains("ROLE_STUDENT")) {
			return Arrays.stream(UserConstant.STUDENT_ACCESS).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private Student getLoggedInUser(Principal principal) {
		return studentRepository.findByFirstName(principal.getName()).get();
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

}
