package com.demo.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long courseId;
	private String title;
	private Integer credit;
}
