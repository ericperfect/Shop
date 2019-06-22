package com.itheima.bean;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> cartItems = new HashMap<String,CartItem>();
	
	private double sum;

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
}
