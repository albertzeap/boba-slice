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
public class Drink implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(nullable=false)
	private String name;
	
	@NotBlank
	@Column(nullable=false)
	private String description;

	@NotBlank
	@Column(columnDefinition="double default 1.99")
	private Double price;
	
	@NotBlank
	@Column(columnDefinition="boolean default false") // 0 is false
	private Boolean veganFiendly;
	
	@NotBlank
	@Column(columnDefinition="boolean default false") // 0 is false
	private Boolean lactoseFriendly;
	
	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "drink", cascade = CascadeType.ALL)
	private List<OrderDrink> orderDrink;
	
	public Drink() {

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

	public Boolean getVeganFiendly() {
		return veganFiendly;
	}

	public void setVeganFiendly(Boolean veganFiendly) {
		this.veganFiendly = veganFiendly;
	}

	public Boolean getLactoseFriendly() {
		return lactoseFriendly;
	}

	public void setLactoseFriendly(Boolean lactoseFriendly) {
		this.lactoseFriendly = lactoseFriendly;
	}

	public List<OrderDrink> getOrderDrink() {
		return orderDrink;
	}

	public void setOrderDrink(List<OrderDrink> orderDrink) {
		this.orderDrink = orderDrink;
	}

	@Override
	public String toString() {
		return "Drink [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", veganFiendly=" + veganFiendly + ", lactoseFriendly=" + lactoseFriendly + ", orderDrink=" + orderDrink + "]";
	}
	
	
	

}
