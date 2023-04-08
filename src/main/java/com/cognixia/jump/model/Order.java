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
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@Column(columnDefinition="double default 0.00")
	private Double totalPrice;
	
	@NotNull
	@Column(nullable=false)
	private Date timeStamp;
	
	@NotNull
	@Column(nullable=false, columnDefinition="boolean default false")
	private Boolean progress;
	

	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserOrder> userOrder;
	
	// One-to-Many relationship between OrderDish
	@JsonProperty( access = Access.WRITE_ONLY )
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderMenuItem> orderMenuItem;

	public Order() {
		
	}

	public Order(Integer id, Double totalPrice, Date timeStamp, Boolean progress) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.timeStamp = timeStamp;
		this.progress = progress;
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

	public List<UserOrder> getUserOrder() {
		return userOrder;
	}


	public void setUserOrder(List<UserOrder> userOrder) {
		this.userOrder = userOrder;
	}
	
	public Boolean getProgress() {
		return progress;
	}

	public void setProgress(Boolean progress) {
		this.progress = progress;
	}

	public List<OrderMenuItem> getOrderDish() {
		return orderMenuItem;
	}

	public void setOrderMenuItem(List<OrderMenuItem> orderMenuItem) {
		this.orderMenuItem = orderMenuItem;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", totalPrice=" + totalPrice + ", timeStamp=" + timeStamp + ", progress=" + progress
				+ ", userOrder=" + userOrder + ", orderMenuItem=" + orderMenuItem + "]";
	}
	
}
