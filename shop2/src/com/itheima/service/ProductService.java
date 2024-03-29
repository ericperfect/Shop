package com.itheima.service;

import java.io.BufferedInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.itheima.bean.Category;
import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.dao.ProductDao;
import com.itheima.utils.DataSourceUtils;



public class ProductService {
	
	//
	public List<Product> findHotProductList() {
		ProductDao dao = new ProductDao();
		List<Product> hotProductList = null;
		try {
			hotProductList = dao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProductList;
	}
	
	//
	public List<Product> findNewProductList() {
		ProductDao dao = new ProductDao();
		List<Product> NewProductList = null;
		try {
			NewProductList = dao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return NewProductList;
	}
	
	//
	public List<Category> findAllCategoryList() {
		ProductDao dao = new ProductDao();
		List<Category> categoryList=null;
		try {
			categoryList = dao.findAllCategoryList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}
	
	//
	public PageBean findProductListByCid(String cid,int currentPage) {
		ProductDao dao = new ProductDao();
		PageBean<Product> pageBean = new PageBean<Product>();
	
		//
		int currentCount = 12;
		
		//
		int totalCount = 0;
		try {
			totalCount = dao.getTotalCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		
		
		//select * from product where cid=? limit ?,?;
		int index = (currentPage-1)*currentCount;
		List<Product> productListByCid = null;
		try {
			productListByCid = dao.findProductListByCid(cid,index,currentCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(productListByCid);
		
		return pageBean;
	}
	
	//
	public Product findProductInfoByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product pro = null;
		try {
			pro = dao.findProductInfoByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}
	
	
	//
	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();
		
		try {
			//
			DataSourceUtils.startTransaction();
			
			dao.addOrder(order);
			
			dao.addOrderItem(order);
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//
	public void updateOrder(Order order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateOrderState(String r6_Order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Order> findAllOrders(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	public List<OrderItem> findOrderItems(String oid) {
		ProductDao dao = new ProductDao();
		List<OrderItem> orderItems = null;
		try {
			orderItems = dao.findOrderItems(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	public Product findProductFromOrderItem(String pid) {
		ProductDao dao = new ProductDao();
		Product pro = null;
		try {
			pro = dao.findProductFromOrderItem(pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
	
	
	public List<Product> findAllByWord(String word)  {
		StringBuilder sb = new StringBuilder();
		List<Object> paramsList = new ArrayList<>();
		if(StringUtils.isBlank(word)){
			return null;
		}
		if(word != null && StringUtils.isNotBlank(word) ){
			StringBuilder wordBuilder = new StringBuilder();
			wordBuilder.append("%");
			//拼凑关键字 比如方伟康   拼凑成 %方%伟%康%
			for(int i = 0;i<word.length();i++){
				wordBuilder.append(word.charAt(i)).append("%");
			}
			
			//汉字匹配
			sb.append("and pname like ?");
			paramsList.add(wordBuilder.toString());
		}
		String condition = sb.toString();
		Object[] params = paramsList.toArray();
		//dao
		ProductDao dao = new ProductDao();
		List<Product> findAllProductByWord = null;
		try {
			findAllProductByWord = dao.findAllProductByWord(condition,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findAllProductByWord;
	}

	public Product getProductByPname(String pname) {
		ProductDao productDao = new ProductDao();
		Product pro = null;
		try {
			pro = productDao.getProductByPname(pname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}

	public List<Product> findProductListByRecommend(String cid) {
		ProductDao productDao = new ProductDao();
		List<Product> proList = null;
		try {
			proList = productDao.findProductListByRecommend(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proList;
	}
	
}












