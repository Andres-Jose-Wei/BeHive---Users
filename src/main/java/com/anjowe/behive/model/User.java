package com.anjowe.behive.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "User")
public class User {

	/**
	 * A user’s id
	 */
	@Id
	private String id;

	/**
	 * A user’s username
	 */
	private String username;

	/**
	 * A user’s email
	 */
	private String email;

	/**
	 * A user’s first name
	 */
	private String firstName;

	/**
	 * A user’s last name
	 */
	private String lastName;

	/**
	 * A user’s skills and the ratings for each skill
	 */
	// private Map<String, List<Double>> skillRatings;
	
	
	
	/**
	 * Number of ratings per skill
	 */
	private Map<String, Integer> numSkillRatings;

	/**
	 * A user’s skills and their average rating for each skill
	 */
	private Map<String, Double> skillStats;

	/**
	 * A user’s rating within the position
	 */
	private double overallRating;

	/**
	 * A user’s personal skill rating
	 */
	private double personalSkillAvg;

	/**
	 * A user’s technical skill rating
	 */
	private double technicalSkillAvg;

	/**
	 * A user’s position within a group
	 */
	private Position position;

	/**
	 * A user’s group
	 */
	private Group group; // a user’s company or department

	/**
	 * A user’s reviews from their superiors
	 */
	private Map<String, List<Review>> reviews;

	/**
	 * The percentage of how many times a user completed all their tasks on time
	 */
	private double punctuality;
	
	/**
	 * The number of times a user had their punctuality rated by another user.
	 */
	private int punctualityCount;

	/**
	 * The number of unique reviewers for a user
	 */
	private int uniqueReviewersCount;

	/**
	 * The number of times a user was awarded “Most Valuable Programmer” of their
	 * team after their project was completed
	 */
	private int mvpCount;

	/**
	 * The number of projects a user worked on
	 */
	private int projectCount;

	/**
	 * Whether or not a user is already working on a project
	 */
	private boolean isAvailable;
	
	/**
	 * Whether or not a user is the Admin of the application
	 */
	private boolean isAdmin;

	public User() {
		//this.skillRatings = new HashMap<String, List<Double>>();
		this.numSkillRatings = new HashMap<String, Integer>();
		this.skillStats = new HashMap<String, Double>();
		this.position = new Position();
		this.reviews = new HashMap<String, List<Review>>();
		this.group = new Group();
	}

}
