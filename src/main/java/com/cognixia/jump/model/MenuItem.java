package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class MenuItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// @NotBlank is only used for String values
	// @NotNull is used for other values 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(nullable=false)
	private String name;
	
	@NotBlank
	@Column(nullable=false)
	private String description;

	@NotNull
	@Column(columnDefinition="double default 1.99")
	private Double price;
	
	@NotNull
	@Column(columnDefinition="boolean default false") // 0 is false
	private Boolean veganFriendly;
	
	@NotNull
	@Column(columnDefinition="boolean default false") // 0 is false
	private Boolean lactoseFriendly;
	
	@NotBlank
	@Column(columnDefinition="varchar(25) check (type in ('Drink', 'Dish'))", nullable=false)
	private String type;
	
	
	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL)
	private List<OrderMenuItem> orderMenuItem;

	public MenuItem() {

	}

	
	public MenuItem(	Integer id, String name, String description,Double price,
						Boolean veganFriendly, Boolean lactoseFriendly, String type
					) 
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.veganFriendly = veganFriendly;
		this.lactoseFriendly = lactoseFriendly;
		this.type = type;
	}

	public MenuItem(	Integer id, String name, String description, Double price, 
						Boolean veganFriendly, Boolean lactoseFriendly, String type,
						List<OrderMenuItem> orderMenuItem
					) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.veganFriendly = veganFriendly;
		this.lactoseFriendly = lactoseFriendly;
		this.type = type;
		this.orderMenuItem = orderMenuItem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getVeganFriendly() {
		return veganFriendly;
	}

	public void setVeganFriendly(Boolean veganFriendly) {
		this.veganFriendly = veganFriendly;
	}

	public Boolean getLactoseFriendly() {
		return lactoseFriendly;
	}

	public void setLactoseFriendly(Boolean lactoseFriendly) {
		this.lactoseFriendly = lactoseFriendly;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<OrderMenuItem> getOrderMenuItem() {
		return orderMenuItem;
	}

	public void setOrderMenuItem(List<OrderMenuItem> orderMenuItem) {
		this.orderMenuItem = orderMenuItem;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", veganFriendly=" + veganFriendly + ", lactoseFriendly=" + lactoseFriendly + ", type=" + type
				+ ", orderMenuItem=" + orderMenuItem + "]";
	}
	
	
}
