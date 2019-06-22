package com.itheima.web.servlet.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itheima.service.AdminOrderService;

public class FindOrderInfoByOidServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String oid = request.getParameter("oid");
		
		AdminOrderService service = new AdminOrderService();
		List<Map<String, Object>> list  = service.findOrderInfoByOid(oid);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("text/html;charset=utf-8");;
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
