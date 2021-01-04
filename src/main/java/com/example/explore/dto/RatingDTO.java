package com.example.explore.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.explore.domain.JobScopeRating;

public class RatingDTO {

	@Min(0)
	@Max(10)
	private Integer score;

	@Size(max = 255)
	private String comment;

	@NotNull
	private Integer clientId;

	public RatingDTO(JobScopeRating jobRating) {
	        this(jobRating.getScore(), jobRating.getComment(), jobRating.getPk().getClientId());
	    }

	private RatingDTO(Integer score, String comment, Integer clientId) {
	        this.score = score;
	        this.comment = comment;
	        this.clientId = clientId;
	    }

	protected RatingDTO() {}

	public Integer getScore() {
		return score;
	}

	public String getComment() {
		return comment;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

}
