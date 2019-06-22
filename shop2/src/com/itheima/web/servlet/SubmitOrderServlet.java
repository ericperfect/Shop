package com.itheima.web.servlet;//提交订单

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.bean.Cart;
import com.itheima.bean.CartItem;
import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.Product;
import com.itheima.bean.User;
import com.itheima.service.ProductService;
import com.itheima.utils.CommonsUtils;

public class SubmitOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		
		//封装order对象
		Order order = new Order();
		
		String oid = CommonsUtils.getUUID();
		order.setOid(oid);
		
		Date date = new Date();
		order.setOrdertime(date);
		
		Cart cart = (Cart) session.getAttribute("cart");
		order.setTotal(cart.getSum());
		
		order.setState(0);
		
		order.setAddress(null);
		order.setName(null);
		order.setTelephone(null);
		
		order.setUser(user);
		
		
		Map<String, CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String, CartItem> entry : cartItems.entrySet()){
			CartItem cartItem = entry.getValue();
			
			OrderItem orderItem = new OrderItem();
			
			//private String itemid;//订单项的id
			orderItem.setItemid(CommonsUtils.getUUID());
			
			//private int count;//订单项内商品的购买数量
			orderItem.setCount(cartItem.getBuyNum());
			
			//private double subtotal;//订单项小计
			orderItem.setSubtotal(cartItem.getSubtotal());
			
			//private Product product;//订单项内部的商品
			orderItem.setProduct(cartItem.getPro());
			
			//private Order order;//该订单项属于哪个订单
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);	
		}
		
		ProductService service = new ProductService();
		service.submitOrder(order);
		
		session.setAttribute("order", order);
		
		response.sendRedirect(request.getContextPath()+"/order_info.jsp");
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}