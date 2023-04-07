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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class Dish implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Column(nullable=false)
	private String description;
	
	@NotBlank
	@Column(columnDefinition="double default 1.99")
	private Double price;
	
	@NotBlank
	@Column(columnDefinition="boolean default false") // 0 is false
	private Boolean veganFriendly;
	
	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
	private List<OrderDish> orderDish;

	public Dish() {

	}
	
	public Dish(Integer id, String name, String description, Double price, Boolean veganFriendly) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.veganFriendly = veganFriendly;
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

	public List<OrderDish> getOrderDish() {
		return orderDish;
	}

	public void setOrderDish(List<OrderDish> orderDish) {
		this.orderDish = orderDish;
	}

	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", veganFriendly=" + veganFriendly + ", orderDish=" + orderDish + "]";
	}
	
	public String toJson() {

		return "{\"id\" : " + id 
				+ ", \"name\" : \"" + name + "\""
				+ ", \"description\" : \"" + description + "\""
				+ ", \"price\" : \"" + price + "\""
				+ ", \"veganFriendly\" : " + veganFriendly;
	}
	
}
