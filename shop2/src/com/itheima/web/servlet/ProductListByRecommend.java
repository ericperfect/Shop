package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itheima.bean.Product;
import com.itheima.service.ProductService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProductListByRecommend
 */
public class ProductListByRecommend extends HttpServlet {
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		ProductService productService = new ProductService();
		List<Product> proList = productService.findProductListByRecommend(cid);
		request.setAttribute("proList", proList);
	/*	Gson gson = new Gson();
		String json = gson.toJson(proList);*/
		String json = JSONArray.fromObject(proList).toString();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
