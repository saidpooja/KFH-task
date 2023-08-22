package com.demo.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.demo.example.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Modifying
	@Transactional
	@Query(value = "update course set credit=?1 where title=?2", nativeQuery = true)
	int updateCourseCreditByCourseName(int courseCredit, String courseTitle);

	@Modifying
	@Transactional
	@Query(value = "update course set credit=?1,title=?2 where course_id=?3", nativeQuery = true)
	int updateCourseCreditByCourseId(int courseCredit, String courseTitle, Long courseId);

	@Modifying
	@Transactional
	@Query(value = "delete from course where title=?1", nativeQuery = true)
	int deleteByCourseName(String courseName);

	@Query("select c from Course c where c.title=?1")
	Course getCourseByTitle(String title);

}
