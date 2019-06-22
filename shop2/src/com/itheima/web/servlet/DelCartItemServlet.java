package com.itheima.web.servlet;//É¾³ý¹ºÎïÏî

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.bean.Cart;
import com.itheima.bean.CartItem;

public class DelCartItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		HttpSession session = request.getSession();
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		Map<String, CartItem> cartItems = cart.getCartItems();
		CartItem remove = cartItems.remove(pid);
		
		double oldSum = cart.getSum();
		double newSum = oldSum - remove.getSubtotal();
		cart.setSum(newSum);
		
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}