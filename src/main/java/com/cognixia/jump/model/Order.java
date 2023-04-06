package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column(columnDefinition="double default 0.00")
	private Double totalPrice;
	
	@NotBlank
	@Column(nullable=false)
	private Date timeStamp;
	
	@NotBlank
	@Column(nullable=false)
	private Boolean progress;
	

	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<UserOrder> userOrder;
	
	// One-to-Many relationship between Dish
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Dish> dishes;
	
	
	// One-to-Many relationship between Drink
	@JsonProperty( access = Access.WRITE_ONLY )
	@JsonIgnore
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<Drink> drinks;

	public Order() {
		
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	public boolean isProgress() {
		return progress;
	}


	public void setProgress(boolean progress) {
		this.progress = progress;
	}


	public List<UserOrder> getUserOrder() {
		return userOrder;
	}


	public void setUserOrder(List<UserOrder> userOrder) {
		this.userOrder = userOrder;
	}

	public List<Dish> getDishes() {
		return dishes;
	}


	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}


	public List<Drink> getDrinks() {
		return drinks;
	}


	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", totalPrice=" + totalPrice + ", timeStamp=" + timeStamp + ", progress=" + progress
				+ ", userOrder=" + userOrder + ", dishes=" + dishes + ", drinks=" + drinks + "]";
	}
	
	
}
