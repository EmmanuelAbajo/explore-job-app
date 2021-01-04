package com.example.explore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job_scope")
public class JobScope {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable= false)
	private String name;

	@Column(nullable= false)
	private String description;

	@Column(nullable= false)
	private Integer renumeration;

	@ManyToOne
	private Freelancer freelancer;

	protected JobScope() {
	}

	public JobScope(String name, String description, Integer renumeration, Freelancer freelancer) {
		super();
		this.name = name;
		this.description = description;
		this.renumeration = renumeration;
		this.freelancer = freelancer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRenumeration() {
		return renumeration;
	}

	public void setRenumeration(Integer renumeration) {
		this.renumeration = renumeration;
	}

	@Override
	public String toString() {
		return "JobScope [id=" + id + ", name=" + name + ", description=" + description + ", renumeration="
				+ renumeration + ", freelancer=" + freelancer + "]";
	}
}
