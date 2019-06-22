package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.service.ProductService;

import net.sf.json.JSONArray;

public class IndexServlet extends HttpServlet {

	//智能推荐
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Product> hotProductList = service.findHotProductList();
		List<Product> newProductList = service.findNewProductList();
		Cookie[] cookies = request.getCookies();
		String cid = null;	
		if(cookies !=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("cid")){
					cid = cookie.getValue();
					break;
				}
			}
			
			int i ;
			for (i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("count")){
					String countStr = cookies[i].getValue();
					int count = Integer.parseInt(countStr);
					count++;
					cookies[i].setValue(String.valueOf(count));
					response.addCookie(cookies[i]);
					break;
				}
			}
			
			if(i == cookies.length){                            //判断第一次打开
				Cookie cookie_count = new Cookie("count", "1"); //页面刷新次数
				response.addCookie(cookie_count);  
			}
				
			
		}
		
		ProductService productService = new ProductService();
		List<Product> proList = productService.findProductListByRecommend(cid);
		request.setAttribute("proList", proList);
		request.setAttribute("hotProductList", hotProductList);
		request.setAttribute("newProductList", newProductList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}