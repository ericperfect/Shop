package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.itheima.bean.User;
import com.itheima.service.UserService;
import com.itheima.utils.CommonsUtils;
import com.itheima.utils.MailUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//解决post提交的乱码问题
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> properties = request.getParameterMap();
		User user = new User();
		try {
			//将字符串的日期，转化成Date格式的
			ConvertUtils.register(new Converter() {
				
				public Object convert(Class clazz, Object value) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date parse = null;
					try {
						parse = format.parse(value.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return parse;
				}
			}, Date.class);
			
			//封装user
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//private String uid
		user.setUid(CommonsUtils.getUUID());
		//private String telephone
		user.setTelephone(null);
		//0:未激活     1:激活
		user.setState(0);
		//激活码
		String activeCode = CommonsUtils.getUUID();
		user.setCode(activeCode);
		
		//将封装好的user传给UserService
		UserService service = new UserService();
		boolean flag = service.regist(user);
		
		//判断是否注册成功
		if(flag){
			/*//注册成功发邮件,激活账号
			String emailMsg = "恭喜您,注册成功!点击链接激活账户<a href='http://localhost:8080/shop/active?activeCode="+user.getCode()+"'>http://localhost:8080/shop/active?activeCode="+user.getCode()+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}*/
			
			//注册成功，重定向到成功页面
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			//注册失败，重定向到失败页面
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}







