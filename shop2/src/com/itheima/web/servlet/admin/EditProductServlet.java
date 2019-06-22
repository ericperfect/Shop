package com.itheima.web.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.service.AdminProductService;

public class EditProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		AdminProductService service = new AdminProductService();
		Product pro = service.findProduct(pid);
		List<Category> categoryList = service.findAllCategory();
		request.setAttribute("pro", pro);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}