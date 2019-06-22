package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.Product;
import com.itheima.bean.User;
import com.itheima.service.ProductService;

public class MyOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		ProductService service = new ProductService();
		// 查询出该用户所有的订单
		List<Order> orderList = service.findAllOrders(user.getUid());

		//
		if (orderList != null) {
			for (Order order : orderList) {
				List<OrderItem> orderItems = service.findOrderItems(order.getOid());
				for (OrderItem orderItem : orderItems) {
					Product pro = service.findProductFromOrderItem(orderItem.getPid());
					orderItem.setProduct(pro);
				}
				order.setOrderItems(orderItems);
			}
		}
		
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}