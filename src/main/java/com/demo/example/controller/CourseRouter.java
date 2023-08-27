package com.demo.example.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.example.entity.Course;
import com.demo.example.impl.CourseService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CourseRouter {

	private static final Logger logger = LoggerFactory.getLogger(Router.class);

	@Autowired
	private CourseService courseService;

	// Course API
	@GetMapping("/course/getAll")
	public List<Course> getCourses() {
		logger.info("Inside getCourse:Entry");
		return courseService.getCourses();

	}

	@PostMapping("/course/addOne")
	public String addCourse(@RequestBody Course course) {
		return courseService.addCourse(course);

	}

	@PutMapping("/course/updateByCourseId")
	public String updateCourse(@RequestParam(name = "title") String courseName,
			@RequestParam(name = "credit") int credit, @RequestParam(name = "courseId") Long courseId) {
		return courseService.updateCourseWithCourseId(credit, courseName, courseId);

	}

	@DeleteMapping("/course/deleteByCourseId")
	public String deleteCourseById(@RequestParam(name = "courseId", required = true) Long courseId) {
		return courseService.deleteCourse(courseId);
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

}
