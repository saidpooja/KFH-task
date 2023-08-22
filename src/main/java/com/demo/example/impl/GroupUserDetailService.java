package com.demo.example.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.demo.example.entity.GroupUserDetails;
import com.demo.example.entity.Student;
import com.demo.example.repository.StudentRepository;

@Service
public class GroupUserDetailService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public UserDetails loadUserByUsername(String studentFirstName) throws UsernameNotFoundException {
		Optional<Student> student = studentRepository.findByFirstName(studentFirstName);
		return student.map(GroupUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException(studentFirstName + " Not Found"));
	}

}
