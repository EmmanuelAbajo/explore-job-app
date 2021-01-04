package com.example.explore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class JobScopeRatingPk implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7612888537243522737L;

	@ManyToOne
	private JobScope jobScope;

	@Column(insertable = false, updatable = false, nullable = false)
	private Integer clientId;

	public JobScopeRatingPk() {
		super();
	}

	public JobScopeRatingPk(JobScope jobScope, Integer clientId) {
		super();
		this.jobScope = jobScope;
		this.clientId = clientId;
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

	@Override
	public String toString() {
		return "JobScopeRatingPk [jobScope=" + jobScope + ", clientId=" + clientId + "]";
	}

}
