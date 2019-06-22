package com.itheima.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.bean.Category;
import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.utils.DataSourceUtils;

public class ProductDao {
	
	public List<Product> findHotProductList() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot = ? order by pdate desc limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				1, 0, 9);
	}

	
	public List<Product> findNewProductList() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate desc limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),
				0, 9);
	}

	public List<Category> findAllCategoryList() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from Category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	
	public int getTotalCount(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long ToalCount = (Long) runner.query(sql, new ScalarHandler(), cid);
		return ToalCount.intValue();
	}

	public List<Product> findProductListByCid(String cid, int index,
			int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM product  WHERE cid=? ORDER BY pdate DESC LIMIT ?,?";
		List<Product> productListByCid = runner.query(sql,
				new BeanListHandler<Product>(Product.class), cid, index,
				currentCount);
		return productListByCid;
	}


	public Product findProductInfoByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product pro = runner.query(sql,
				new BeanHandler<Product>(Product.class), pid);
		return pro;
	}

	
	public void addOrder(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();

		runner.update(conn, sql, order.getOid(), order.getOrdertime(),
				order.getTotal(), order.getState(), order.getAddress(),
				order.getName(), order.getTelephone(), order.getUser().getUid());
	}

	
	public void addOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Connection conn = DataSourceUtils.getConnection();
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			runner.update(conn, sql, orderItem.getItemid(),orderItem.getCount(),
					orderItem.getSubtotal(),orderItem.getProduct().getPid(),orderItem.getOrder().getOid());
		}
		
	}
	
	
	public void updateOrder(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set address=?,name=?,telephone=? where oid = ?";
		runner.update(sql, order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}
	
	//
	public void updateOrderState(String r6_Order) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state=? where oid = ?";
		runner.update(sql, 1,r6_Order);
	}

	public List<Order> findAllOrders(String uid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid = ?";
		List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
		return orderList;
	}

	public List<OrderItem> findOrderItems(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orderitem where oid = ?";
		List<OrderItem> orderItems = runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
		return orderItems;
	}

	public Product findProductFromOrderItem(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product pro = runner.query(sql, new BeanHandler<Product>(Product.class), pid);
		return pro;
	}
	
	//鏍规嵁鍏抽敭瀛楁煡璇roduct
	public List<Product> findAllProductByWord(String condition, Object[] params) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select pid,cid,pname from product where 1=1 " + condition+ " limit 0,5";
		
		return runner.query(sql, new BeanListHandler<Product>(Product.class), params);
	
	}

	public Product getProductByPname(String pname) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pname like ?";
		return runner.query(sql, new BeanHandler<Product>(Product.class),"%"+pname+"%");
	}
	
	//鑾峰彇鎺ㄨ崘鍟嗗搧
	public List<Product> findProductListByRecommend(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT pid,pname,shop_price,pimage,cid FROM product WHERE cid = ? ORDER BY pdate DESC LIMIT ?,?";
	
		return runner.query(sql, new BeanListHandler<Product>(Product.class),cid,0,4);
	}

}
