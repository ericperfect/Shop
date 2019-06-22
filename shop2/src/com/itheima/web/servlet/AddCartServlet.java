package com.itheima.web.servlet;//添加购物车

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.bean.Cart;
import com.itheima.bean.CartItem;
import com.itheima.bean.Product;
import com.itheima.service.ProductService;

public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ProductService service = new ProductService();
		HttpSession session = request.getSession();
		
		//封装cartItem对象
		String pid = request.getParameter("pid");//商品主键
		
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		
		Product pro = service.findProductInfoByPid(pid);
		
		double subtotal = pro.getShop_price()*buyNum;
		
		CartItem item = new CartItem();
		item.setPro(pro);		   
		item.setBuyNum(buyNum);	   //购买的数量
		item.setSubtotal(subtotal);//小计
		
		//获得购物车    判断session中是否已经存在购物车
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
		}
		
		//将购物项放入购物车中
		//放入之前，先判断是否已经存在于购物车中
		Map<String, CartItem> cartItems = cart.getCartItems();
		//如果包含，只增加购物项中该商品的数量和小计和购物车中的总计金额
		if(cartItems.containsKey(pid)){
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();
			double oldSubtotal = cartItem.getSubtotal();
			int newBuyNum = oldBuyNum + buyNum;
			double newSubtotal = oldSubtotal + subtotal;
			cartItem.setBuyNum(newBuyNum);
			cartItem.setSubtotal(newSubtotal);
		}else{
			//新的商品直接放入购物车
			cart.getCartItems().put(pro.getPid(), item);
		}

		double sum = cart.getSum()+subtotal;
	
		cart.setSum(sum);
		//将车再次放入session中
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}