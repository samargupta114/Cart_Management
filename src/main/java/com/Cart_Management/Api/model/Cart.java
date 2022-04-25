package com.Cart_Management.Api.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
	
	@Id
	@GeneratedValue
	private int cartid;
	private String 	customer_name;
	private String email;
	private String gender; 
	
	@OneToMany(targetEntity = Item.class,cascade = CascadeType.ALL)
	@JoinColumn(name="cart_item_fk",referencedColumnName = "cartid")
	private java.util.List<Item> items;

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public java.util.List<Item> getItems() {
		return items;
	}

	public void setItems(java.util.List<Item> items) {
		this.items = items;
	}

	
	

}
