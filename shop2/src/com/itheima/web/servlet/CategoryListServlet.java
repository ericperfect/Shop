
package com.itheima.web.servlet;//·ÖÀà

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.itheima.bean.Category;
import com.itheima.service.ProductService;
import com.itheima.utils.JedisPoolUtil;

public class CategoryListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Category> categoryList = service.findAllCategoryList();
		Gson gson = new Gson();
		String categoryListJson = gson.toJson(categoryList);

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(categoryListJson);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}