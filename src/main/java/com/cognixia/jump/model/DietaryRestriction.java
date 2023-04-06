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
	@OneToMany(mappedBy = "dietary_restriction", cascade = CascadeType.ALL)
	private List<UserDietaryRestriction> userDietaryDescription;

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

	public List<UserDietaryRestriction> getUserDietaryDescription() {
		return userDietaryDescription;
	}

	public void setUserDietaryDescription(List<UserDietaryRestriction> userDietaryDescription) {
		this.userDietaryDescription = userDietaryDescription;
	}

	@Override
	public String toString() {
		return "DietaryRestriction [id=" + id + ", description=" + description + ", userDietaryDescription="
				+ userDietaryDescription + "]";
	}
	

}
