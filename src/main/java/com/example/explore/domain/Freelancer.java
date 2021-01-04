package com.example.explore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "freelancer")
public class Freelancer {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable= false)
	String firstName;

	@Column(nullable= false)
	String lastName;

	@Column(nullable= false)
	@Enumerated(value = EnumType.STRING)
	private ExperienceLevel experienceLevel;

	protected Freelancer() {
	}

	public Freelancer(String firstName, String lastName, ExperienceLevel experienceLevel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.experienceLevel = experienceLevel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(ExperienceLevel experienceLevel) {
		this.experienceLevel = experienceLevel;
	}

	@Override
	public String toString() {
		return "Freelancer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", experienceLevel="
				+ experienceLevel;
	}

}
