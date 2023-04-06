package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	// we'll primarily be using User
	// Admin roles can be implemented in the future
	public static enum Role {
		ROLE_USER, ROLE_ADMIN
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@NotBlank 
	private String username;
	
	@NotBlank
	// minimum of 8 characters, at least 1 letter and 1 number
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") 
	@Column(unique = true, nullable = false)
	private String password;

	// For security purposes
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	// Determines if the account it still active
	@Column(columnDefinition = "boolean default true")
	private boolean enabled; // true or false if user is enabled currently
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	// unique = true     ==> has a unique constraint
	// nullable = false  ==> NOT NULL constraint
	@Pattern(regexp="^.+@.+$")
	@Column(unique = true, nullable = false)
	private String email;
	
	@NotBlank
	@Pattern(regexp="^[0-9]{16}$")
	@Column(nullable = false)
	private String paymentCard;
	
	@NotBlank
	@Pattern(regexp="^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")
	private String phoneNumber;
	
	// Set up the one to many relationship between the relationship table and user
	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserDietaryRestriction> dietaryRestriction;
	
	// Set up the one to many relationship between the relationship table and user
	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserOrder> user_order;

	public User() {

	}


	public User(Integer id, String username, String password, String firstName, String lastName, String email, String paymentCard, String phoneNumber, List<UserDietaryRestriction> dietaryRestriction, List<UserOrder> user_order) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.paymentCard = paymentCard;
		this.phoneNumber = phoneNumber;
		this.dietaryRestriction = dietaryRestriction;
		this.user_order = user_order;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaymentCard() {
		return paymentCard;
	}

	public void setPaymentCard(String paymentCard) {
		this.paymentCard = paymentCard;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<UserDietaryRestriction> getDietaryRestriction() {
		return dietaryRestriction;
	}

	public void setDietaryRestriction(List<UserDietaryRestriction> dietaryRestriction) {
		this.dietaryRestriction = dietaryRestriction;
	}

	public List<UserOrder> getUser_order() {
		return user_order;
	}

	public void setUser_order(List<UserOrder> user_order) {
		this.user_order = user_order;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", paymentCard=" + paymentCard + ", phoneNumber="
				+ phoneNumber + ", dietaryRestriction=" + dietaryRestriction + ", user_order=" + user_order + "]";
	}


	

	

}
