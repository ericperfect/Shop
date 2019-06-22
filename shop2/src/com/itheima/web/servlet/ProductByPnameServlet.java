package com.itheima.web.servlet;//ËÑË÷

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.dao.CategoryDao;
import com.itheima.service.ProductService;

/**
 * Servlet implementation class ProductByPnameServlet
 */
public class ProductByPnameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("pname");
		ProductService productService = new ProductService();
		Product pro = productService.getProductByPname(pname);
		CategoryDao dao = new CategoryDao();
		Category category = null;
		try {
			category = dao.getCategoryName(pro.getCid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pro.setCategory(category);
		request.setAttribute("pro", pro);
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
