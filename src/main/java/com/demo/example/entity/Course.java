package com.demo.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long courseId;
	private String title;
	private Integer credit;

	public Course(Long courseId, String title, Integer credit) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.credit = credit;
	}

	public Course() {
		super();
	}

	public Course(String title, Integer credit) {
		super();
		this.title = title;
		this.credit = credit;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", title=" + title + ", credit=" + credit + "]";
	}
}
