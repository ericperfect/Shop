package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.dao.AdminProductDao;

public class AdminProductService {

	public List<Product> findAllProduct() {
		AdminProductDao dao = new AdminProductDao();
		List<Product> productList = null;
		try {
			productList = dao.findAllProduct();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	
	//Àà±ð
	public List<Category> findAllCategory() {
		AdminProductDao dao = new AdminProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}

	public void addProduct(Product pro) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.addProduct(pro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean delProduct(String pid) {
		AdminProductDao dao = new AdminProductDao();
		int delProduct = 0;
		try {
			delProduct = dao.delProduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return delProduct==0?false:true;
	}

	public Product findProduct(String pid) {
		AdminProductDao dao = new AdminProductDao();
		Product pro = null;
		try {
			pro = dao.findProduct(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  pro;
	}

	public void updateProduct(Product pro) {
		AdminProductDao dao = new AdminProductDao();
		try {
			dao.updateProduct(pro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
