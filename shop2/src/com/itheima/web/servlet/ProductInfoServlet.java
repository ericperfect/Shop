package com.itheima.web.servlet;//…Ã∆∑œÍ«È

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.dao.CategoryDao;
import com.itheima.service.ProductService;

public class ProductInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		ProductService service = new ProductService();
		Product pro = service.findProductInfoByPid(pid);
		CategoryDao dao = new CategoryDao();
		Category category =null;
		try {
			 category = dao.getCategoryName(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pro.setCategory(category);
		request.setAttribute("pro", pro);
		request.setAttribute("cid", cid);
		request.setAttribute("currentPage", currentPage);
		
		//
		Cookie[] cookies = request.getCookies();
		String pids = pid;
		//
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if("pids".equals(cookie.getName())){
					pids = cookie.getValue();
					String[] split = pids.split("-");
					List<String> asList = Arrays.asList(split);
					LinkedList<String> list = new LinkedList<String>(asList);
					
					//
					if(list.contains(pid)){
						list.remove(pid);
					}
					list.addFirst(pid);
					
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<list.size() && i<7;i++){
						if(i == list.size()-1){
							sb.append(list.get(i));
						}else{
							sb.append(list.get(i));
							sb.append("-");
						}
					}					
					pids = sb.toString();
				}
				
			}
		}
		
		Cookie cookie_pids = new Cookie("pids", pids);
		
		response.addCookie(cookie_pids);
		
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}