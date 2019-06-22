package com.itheima.web.servlet;//分页

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.service.ProductService;

public class ProductListByCidServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null){
			currentPageStr = "1";
		}
		int currentPage = Integer.parseInt(currentPageStr);
		ProductService service = new ProductService();
		PageBean pageBean = service.findProductListByCid(cid,currentPage);
		
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		//得到客户端所有的cookie
		Cookie[] cookies = request.getCookies();
		//cookies为空，我们直接存储cookie
		Cookie cookie_cid = new Cookie("cid", cid);
		cookie_cid.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie_cid);
		
		
		//浏览记录
		List<Product> historyList = new ArrayList<Product>();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				if("pids".equals(cookie.getName())){
					String pids = cookie.getValue();
					String[] split = pids.split("-");
					for (String str : split) {
						Product pro = service.findProductInfoByPid(str);
						historyList.add(pro);
					}
				}
			}
		}
		
		request.setAttribute("historyList", historyList);
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
