package com.itheima.web.servlet.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.bean.AdminUser;
import com.itheima.utils.DataSourceUtils;

/**
 * Servlet implementation class AdminUser
 */
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from adminuser where username=? and password = ?";
		AdminUser adminUser = null;
		try {
			adminUser = queryRunner.query(sql, new BeanHandler<AdminUser>(AdminUser.class),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(adminUser == null){
			//request.setAttribute("wrong", "用户名或者密码不正确");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("用户名或者密码不正确");
			//request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("adminuser", adminUser);
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
			//response.sendRedirect("/admin/home.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
