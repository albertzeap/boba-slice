package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class DietaryRestriction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String description; // describes the dietary constraint
	
	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "dietaryRestriction", cascade = CascadeType.ALL)
	private List<UserDietaryRestriction> userDietaryRestriction;

	public DietaryRestriction() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserDietaryRestriction> getuserDietaryRestriction() {
		return userDietaryRestriction;
	}

	public void setuserDietaryRestriction(List<UserDietaryRestriction> userDietaryRestriction) {
		this.userDietaryRestriction = userDietaryRestriction;
	}

	@Override
	public String toString() {
		return "DietaryRestriction [id=" + id + ", description=" + description + ", userDietaryRestriction="
				+ userDietaryRestriction + "]";
	}
	

}
