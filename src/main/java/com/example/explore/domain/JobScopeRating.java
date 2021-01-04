package com.example.explore.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class JobScopeRating {
	
	@EmbeddedId
	private JobScopeRatingPk pk;
	
	@Column(nullable= false)
	private Integer score;
	
	@Column
	private String comment;
	
	protected JobScopeRating() {
		super();
	}

	public JobScopeRating(JobScopeRatingPk pk, Integer score, String comment) {
		super();
		this.pk = pk;
		this.score = score;
		this.comment = comment;
	}

	public JobScopeRatingPk getPk() {
		return pk;
	}

	public void setPk(JobScopeRatingPk pk) {
		this.pk = pk;
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

	@Override
	public String toString() {
		return "JobScopeRating [pk=" + pk + ", score=" + score + ", comment=" + comment + "]";
	}
}
