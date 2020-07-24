package com.anjowe.behive.model;

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
@Document(collection = "Position")
public class Position {
	/**
	 * The job position of a user
	 */
	@Id
	private String name;

	public Position() {
		this.name = "";
	}
}
