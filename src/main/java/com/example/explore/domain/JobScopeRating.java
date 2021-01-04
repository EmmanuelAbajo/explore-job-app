package com.example.explore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job_scope_rating")
public class JobScopeRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "job_scope_id")
	private JobScope jobScope;

	@Column(name = "client_id")
	private Integer clientId;

	@Column(nullable = false)
	private Integer score;

	@Column
	private String comment;

	protected JobScopeRating() {
		super();
	}

	public JobScopeRating(JobScope jobScope, Integer clientId, Integer score) {
		super();
		this.jobScope = jobScope;
		this.clientId = clientId;
		this.score = score;
		this.comment = toComment(score);

	}

	public JobScopeRating(JobScope jobScope, Integer clientId, Integer score, String comment) {
		super();
		this.jobScope = jobScope;
		this.clientId = clientId;
		this.score = score;
		this.comment = comment;
	}

	/**
	 * Auto Generate a message for a score.
	 *
	 * @param score
	 * @return
	 */
	private String toComment(Integer score) {
		switch (score) {
		case 1:
			return "Terrible";
		case 2:
			return "Poor";
		case 3:
			return "Fair";
		case 4:
			return "Good";
		case 5:
			return "Great";
		default:
			return score.toString();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobScope getJobScope() {
		return jobScope;
	}

	public void setJobScope(JobScope jobScope) {
		this.jobScope = jobScope;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
