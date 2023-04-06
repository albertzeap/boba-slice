package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserDietaryRestriction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn( name = "restriction_id", referencedColumnName = "id")
	private DietaryRestriction dietaryRestriction;

	public UserDietaryRestriction() {

	}

	public UserDietaryRestriction(Integer id, User user, DietaryRestriction dietaryRestriction) {
		super();
		this.id = id;
		this.user = user;
		this.dietaryRestriction = dietaryRestriction;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DietaryRestriction getDietaryRestriction() {
		return dietaryRestriction;
	}

	public void setDietaryRestriction(DietaryRestriction dietaryRestriction) {
		this.dietaryRestriction = dietaryRestriction;
	}

	@Override
	public String toString() {
		return "UserDietaryRestriction [id=" + id + ", user=" + user + ", dietaryRestriction=" + dietaryRestriction + "]";
	}

}
