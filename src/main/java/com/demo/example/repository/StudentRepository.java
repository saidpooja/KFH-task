package com.demo.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.demo.example.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
//	@Query("SELECT new com.demo.example.entity.StudentCourses(s.firstName,c.title) from Student s join s.courses c")
//	public List<StudentCourses> getStudentWithCourse();

	public Optional<Student> findByFirstName(String firstName);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student set last_name=?1 where email_address=?2", nativeQuery = true)
	int updateStudentNameByEmailId(String firstName, String emailId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student set roles=?1 where student_id=?2", nativeQuery = true)
	int updateRoleByStudentId(String role, Long studentId);

}
