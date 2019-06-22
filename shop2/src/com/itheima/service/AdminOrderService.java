package com.itheima.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.bean.Order;
import com.itheima.dao.AdminOrderDao;


public class AdminOrderService {

	public List<Order> findAllOrders() {
		AdminOrderDao dao = new AdminOrderDao();
		 List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		AdminOrderDao dao = new AdminOrderDao();
		List<Map<String, Object>> list =null;
		try {
			list = dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
