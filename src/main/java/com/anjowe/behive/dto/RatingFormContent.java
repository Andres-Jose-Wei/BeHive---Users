package com.anjowe.behive.dto;

import java.util.Map;

import com.anjowe.behive.model.Review;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RatingFormContent {
	
	/**
	 * A user's review for a particular project member
	 */
	private Review review;
	
	/**
	 * A user's ratings for a particular project members skills
	 */
	private Map<String, Double> skillRating;
	
}
