package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(columnDefinition="DEFAULT '1.99'")
	private Double price;
	
	@NotBlank
	@Column(columnDefinition="DEFAULT 0") // 0 is false
	private boolean veganFriendly;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn( name = "order_id", referencedColumnName = "id")
	private Order order;

	public Dish() {

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

	public boolean isVeganFriendly() {
		return veganFriendly;
	}

	public void setVeganFriendly(boolean veganFriendly) {
		this.veganFriendly = veganFriendly;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", veganFriendly=" + veganFriendly + ", order=" + order + "]";
	}
	

}
