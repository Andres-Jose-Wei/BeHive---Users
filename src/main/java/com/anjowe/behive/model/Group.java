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
@Document(collection = "Group")
public class Group {
	/**
	 * The name of user's group
	 */
	@Id
	private String name;

	public Group() {
		this.name = "";
	}
}
