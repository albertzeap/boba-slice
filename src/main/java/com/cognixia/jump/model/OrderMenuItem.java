package com.cognixia.jump.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class OrderMenuItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn( name = "order_id", referencedColumnName = "id")
	@JsonIgnore
	private Order order;
	
	@ManyToOne
	@JoinColumn( name = "menu_item_id", referencedColumnName = "id")
	@JsonIgnore
	private MenuItem menuItem;

	public OrderMenuItem() {

	}
	
	public OrderMenuItem(Integer id, Order order, MenuItem menuItem) {
		super();
		this.id = id;
		this.order = order;
		this.menuItem = menuItem;
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

	public MenuItem getDish() {
		return menuItem;
	}

	public void setDish(MenuItem dish) {
		this.menuItem = dish;
	}

	@Override
	public String toString() {
		return "OrderDish [id=" + id + ", order=" + order + ", MenuItem=" + menuItem + "]";
	}

	public String toJson(){
		return "{\"id\" : " + id 
				+ ", \"order\" : \"" + order + "\""
				+ ", \"MenuItem\" : \"" + menuItem + "\"}";
	}

}
