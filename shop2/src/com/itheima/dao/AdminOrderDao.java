	package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.itheima.bean.Order;
import com.itheima.utils.DataSourceUtils;

public class AdminOrderDao {

	public List<Order> findAllOrders() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders";
		List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class));
		return orderList;
	}

	public List<Map<String, Object>> findOrderInfoByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select p.pimage,p.pname,p.shop_price,i.count,i.subtotal "
				+ 	 "from product p,orderitem i "
				+    "where p.pid = i.pid and oid=?";
		List<Map<String, Object>> list = runner.query(sql, new MapListHandler(),oid);
		return list;
	}
	
}
