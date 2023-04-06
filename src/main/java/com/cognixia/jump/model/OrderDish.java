package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class OrderDish implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "order_id", referencedColumnName = "id")
	private Order order;
	
	@ManyToOne
	@JoinColumn( name = "dish_id", referencedColumnName = "id")
	private Dish dish;

	public OrderDish() {

	}
	
	public OrderDish(Integer id, Order order, Dish dish) {
		super();
		this.id = id;
		this.order = order;
		this.dish = dish;
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

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	@Override
	public String toString() {
		return "OrderDish [id=" + id + ", order=" + order + ", dish=" + dish + "]";
	}

}
