package com.demo.example.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.example.entity.Course;
import com.demo.example.repository.CourseRepository;

@Service
public class CourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getCourses() {
		logger.info("Inside CourseService.getCourse:Entry");
		List<Course> courseObj = courseRepository.findAll();
		logger.info("Inside CourseService.getCourse:Exit");
		return courseObj;
	}

	public String addCourse(Course course) {
		String outputMessage = "";
		try {
			logger.info("Inside CourseService.addCourse:Entry");
			Course courseObj = courseRepository.save(course);
			if (courseObj != null) {
				outputMessage = "Course added successfully";
			} else {
				outputMessage = "Please try to add again";
			}
			logger.info("Inside CourseService.addCourse:Exit");
		} catch (Exception e) {
			logger.error("Inside CourseService.addCourse:Exception: " + e.getMessage());
			outputMessage = "An error occurred while adding the course: " + e.getMessage();
		}
		return outputMessage;

	}

	public String updateCourseWithCourseId(int credit, String title, Long courseId) {
		logger.info("Inside CourseService.updateCourseWithCourseId:Entry");
		String outputMessage = "Please select right course";
		try {
			Optional<Course> course = courseRepository.findById(courseId);
			if(course.isPresent()) {
				int updatedRecord = courseRepository.updateCourseCreditByCourseId(credit, title, courseId);
				if (updatedRecord > 0) {
					outputMessage= "Course updated successfully";
				}
			}else {
				outputMessage= "Please select right course";
			}
		} catch (Exception ex) {
			logger.error("Inside CourseService.updateCourseWithCourseId:Exception: " + ex.getMessage());
			outputMessage= "An error occurred while updating the course: " + ex.getMessage();
		}
		return outputMessage;
	}

	public String deleteCourse(Long courseId) {
		logger.info("Inside CourseService.deleteCourse:Entry");
		String outputMessage = "";
		try {
			Optional<Course> course = courseRepository.findById(courseId);
			if (course.isPresent()) {
				courseRepository.deleteById(courseId);
				outputMessage = "Course deleted successfully";
			} else {
				outputMessage = "Please enter valid course id";
			}
			logger.info("Inside CourseService.deleteCourse:Exit");
		} catch (Exception ex) {
			logger.error("Inside CourseService.deleteCourse:Exception: " + ex.getMessage());
			outputMessage = "An error occurred while deleting the course: " + ex.getMessage();
		}
		return outputMessage;
	}

	public CourseRepository getCourseRepository() {
		return courseRepository;
	}

	public void setCourseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

}
