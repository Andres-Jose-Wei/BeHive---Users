package com.anjowe.behive.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "User")
public class User{

/**
 * A user’s username
 */
@Id
private String username;

/**
 * A user’s password
 */
private String password;

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
 * A user’s skills and their rating for each skill 
 */
private Map<Skill, Rating> skillStats;

/**
 * A user’s rating within the position
 */
private Rating rating;

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
private Map<User, Review> reviews; 

/**
 * The number of times a user was awarded “Most Valuable Programmer” of their team after their project was completed
 */
private Integer mvpCount;

/**
 * The percentage of how many times a user completed all their tasks on time
 */
private Double punctuality;

/**
 * The number of reviews for a user
 */
private Integer reviewsCount;

/**
 * The number of unique reviewers for a user
 */
private Integer uniqueReviewersCount;

/**
 * The number of projects a user worked on
 */
private Integer projectCount;

/**
 * The average number of tasks a user completes per project
 */
private Double avgTasks;

/**
 * The average number of days it takes a user to complete a task 
 */
private Integer avgTaskDuration;

}
