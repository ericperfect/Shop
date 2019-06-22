package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.itheima.bean.Product;
import com.itheima.service.ProductService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class ProductFindByWordServlet
 */
public class ProductFindByWordServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		//获得关键字
		String word = request.getParameter("word");
		//查询
		ProductService service = new ProductService();
		List<Product> allProByWord = service.findAllByWord(word);
		//根据关键字，查询到所有有关的商品后，转化成json
		String jsonArray = JSONArray.fromObject(allProByWord).toString();
		response.getWriter().println(jsonArray);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
