package com.example.hackerrank.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/* This class represents the Person Entity */
@Entity
public class PersonEntity {

	@JsonProperty("id")
	@GeneratedValue
	@Id
	private Integer id;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("age")
	private String age;
	
	@JsonProperty("favourite_colour")
	private String favouriteColour;
	
	@JsonProperty("hobby")
	private ArrayList<String> hobby;
	
	public String getFavoriteColour() {
		return favouriteColour;
	}
	
	public void setFavoriteColour(String favouriteColour) {
		this.favouriteColour = favouriteColour;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public ArrayList<String> getHobby() {
		return hobby;
	}
	
	public void setHobby(ArrayList<String> hobby) {
		this.hobby = hobby;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
