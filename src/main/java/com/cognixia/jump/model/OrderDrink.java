package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDrink implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "order_id", referencedColumnName = "id")
	private Order order;
	
	@ManyToOne
	@JoinColumn( name = "drink_id", referencedColumnName = "id")
	private Drink drink;

	public OrderDrink() {
		
	}
	
	public OrderDrink(Integer id, Order order, Drink drink) {
		super();
		this.id = id;
		this.order = order;
		this.drink = drink;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	@Override
	public String toString() {
		return "OrderDrink [id=" + id + ", order=" + order + ", drink=" + drink + "]";
	}
	

}
