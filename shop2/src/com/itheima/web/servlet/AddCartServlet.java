package com.itheima.web.servlet;//��ӹ��ﳵ

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
		
		//��װcartItem����
		String pid = request.getParameter("pid");//��Ʒ����
		
		int buyNum = Integer.parseInt(request.getParameter("buyNum"));
		
		Product pro = service.findProductInfoByPid(pid);
		
		double subtotal = pro.getShop_price()*buyNum;
		
		CartItem item = new CartItem();
		item.setPro(pro);		   
		item.setBuyNum(buyNum);	   //���������
		item.setSubtotal(subtotal);//С��
		
		//��ù��ﳵ    �ж�session���Ƿ��Ѿ����ڹ��ﳵ
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
		}
		
		//����������빺�ﳵ��
		//����֮ǰ�����ж��Ƿ��Ѿ������ڹ��ﳵ��
		Map<String, CartItem> cartItems = cart.getCartItems();
		//���������ֻ���ӹ������и���Ʒ��������С�ƺ͹��ﳵ�е��ܼƽ��
		if(cartItems.containsKey(pid)){
			CartItem cartItem = cartItems.get(pid);
			int oldBuyNum = cartItem.getBuyNum();
			double oldSubtotal = cartItem.getSubtotal();
			int newBuyNum = oldBuyNum + buyNum;
			double newSubtotal = oldSubtotal + subtotal;
			cartItem.setBuyNum(newBuyNum);
			cartItem.setSubtotal(newSubtotal);
		}else{
			//�µ���Ʒֱ�ӷ��빺�ﳵ
			cart.getCartItems().put(pro.getPid(), item);
		}

		double sum = cart.getSum()+subtotal;
	
		cart.setSum(sum);
		//�����ٴη���session��
		session.setAttribute("cart", cart);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}