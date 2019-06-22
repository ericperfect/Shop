package com.itheima.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.AdminProductService;

public class DelProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		AdminProductService service = new AdminProductService();
		boolean delProduct = service.delProduct(pid);
		//request.setAttribute("delProduct", delProduct);
		
		//response.sendRedirect(request.getContextPath()+"/adminProductList");
		request.getRequestDispatcher("/adminProductList").forward(request, response);
		System.out.println(request.getContextPath());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}