package com.itheima.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.utils.DataSourceUtils;


public class AdminProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate desc";
		List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return productList;
	}
	
	
	public List<Category> findAllCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));
		return categoryList;
	}

	public void addProduct(Product pro) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pdate = dateFormat.format(pro.getPdate());
		runner.update(sql,pro.getPid(),pro.getPname(),pro.getMarket_price(),pro.getShop_price(),pro.getPimage(),pdate,pro.getIs_hot(),pro.getPdesc(),pro.getPflag(),pro.getCid());
	}

	public int delProduct(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where pid=?";
		int delProduct = runner.update(sql, pid);
		return delProduct;
	}

	public Product findProduct(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		Product pro = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return pro;
	}

	public void updateProduct(Product pro) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update product set  pname=?,is_hot=?,market_price=?,shop_price=?,pdesc=?,cid=? where pid=?";
		runner.update(sql, pro.getPname(),pro.getIs_hot(),pro.getMarket_price(),pro.getShop_price(),pro.getPdesc(),pro.getCid(),pro.getPid());
		
	}

}
